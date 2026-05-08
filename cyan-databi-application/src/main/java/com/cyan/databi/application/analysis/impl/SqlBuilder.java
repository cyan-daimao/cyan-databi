package com.cyan.databi.application.analysis.impl;

import com.cyan.databi.application.analysis.cmd.AnalysisCmd;
import com.cyan.datametric.client.dto.MetricBiAnalysisCmd;
import com.cyan.datametric.client.dto.MetricBiAnalysisCmd.MetricRef;
import com.cyan.datametric.client.dto.MetricBiAnalysisCmd.DimensionRef;
import com.cyan.datametric.client.dto.MetricBiAnalysisCmd.FilterRef;
import com.cyan.datametric.client.dto.MetricBiAnalysisCmd.OrderRef;
import com.cyan.databi.domain.chart.valobj.DimensionConfigValObj;
import com.cyan.databi.domain.chart.valobj.MetricConfigValObj;
import com.cyan.databi.domain.chart.valobj.FilterConfigValObj;
import com.cyan.databi.domain.chart.valobj.OrderConfigValObj;
import com.cyan.databi.domain.dataset.Dataset;
import com.cyan.databi.enums.AggregateType;
import com.cyan.databi.enums.DatasetSourceType;
import com.cyan.databi.enums.FilterOperator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SQL生成器
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Component
public class SqlBuilder {

    /**
     * 根据数据集和分析配置生成SQL
     */
    public String build(Dataset dataset, AnalysisCmd cmd) {
        String fromClause = buildFromClause(dataset);
        List<String> selectColumns = buildSelectColumns(cmd);
        List<String> whereConditions = buildWhereConditions(cmd);
        List<String> groupByColumns = buildGroupByColumns(cmd);
        List<String> orderByColumns = buildOrderByColumns(cmd);

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(String.join(", ", selectColumns));
        sql.append(" FROM ").append(fromClause);

        if (!whereConditions.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", whereConditions));
        }

        if (!groupByColumns.isEmpty()) {
            sql.append(" GROUP BY ").append(String.join(", ", groupByColumns));
        }

        if (!orderByColumns.isEmpty()) {
            sql.append(" ORDER BY ").append(String.join(", ", orderByColumns));
        }

        if (cmd.getLimitValue() != null && cmd.getLimitValue() > 0) {
            sql.append(" LIMIT ").append(cmd.getLimitValue());
        }

        return sql.toString();
    }

    /**
     * 构建FROM子句
     */
    private String buildFromClause(Dataset dataset) {
        if (dataset.getSourceType() == DatasetSourceType.TABLE) {
            return dataset.getSourceTable();
        } else {
            return "(" + dataset.getSourceSql() + ") AS t";
        }
    }

    /**
     * 构建SELECT列
     */
    private List<String> buildSelectColumns(AnalysisCmd cmd) {
        List<String> columns = new ArrayList<>();

        // 维度列
        Optional.ofNullable(cmd.getDimensions()).orElse(List.of()).forEach(dim -> {
            if (dim.getAlias() != null && !dim.getAlias().isEmpty()) {
                columns.add(dim.getField() + " AS `" + dim.getAlias() + "`");
            } else {
                columns.add(dim.getField());
            }
        });

        // 指标列
        Optional.ofNullable(cmd.getMetrics()).orElse(List.of()).forEach(metric -> {
            String expr = buildAggregateExpr(metric);
            if (metric.getAlias() != null && !metric.getAlias().isEmpty()) {
                columns.add(expr + " AS `" + metric.getAlias() + "`");
            } else {
                columns.add(expr);
            }
        });

        // 如果没有维度也没有指标，默认查询所有
        if (columns.isEmpty()) {
            columns.add("*");
        }

        return columns;
    }

    /**
     * 构建聚合表达式
     */
    private String buildAggregateExpr(MetricConfigValObj metric) {
        AggregateType aggregate = metric.getAggregate();
        String field = metric.getField();
        if (aggregate == null) {
            return field;
        }
        return switch (aggregate) {
            case SUM -> "SUM(" + field + ")";
            case AVG -> "AVG(" + field + ")";
            case COUNT -> "COUNT(" + field + ")";
            case MAX -> "MAX(" + field + ")";
            case MIN -> "MIN(" + field + ")";
            case COUNT_DISTINCT -> "COUNT(DISTINCT " + field + ")";
        };
    }

    /**
     * 构建WHERE条件
     */
    private List<String> buildWhereConditions(AnalysisCmd cmd) {
        List<String> conditions = new ArrayList<>();
        Optional.ofNullable(cmd.getFilters()).orElse(List.of()).forEach(filter -> {
            String condition = buildFilterCondition(filter);
            if (condition != null) {
                conditions.add(condition);
            }
        });
        return conditions;
    }

    /**
     * 构建单个过滤条件
     */
    private String buildFilterCondition(FilterConfigValObj filter) {
        String field = filter.getField();
        FilterOperator operator = filter.getOperator();
        List<String> values = filter.getValues();

        if (operator == null) {
            return null;
        }

        return switch (operator) {
            case EQ -> field + " = " + quoteValue(values, 0);
            case NE -> field + " != " + quoteValue(values, 0);
            case GT -> field + " > " + quoteValue(values, 0);
            case GTE -> field + " >= " + quoteValue(values, 0);
            case LT -> field + " < " + quoteValue(values, 0);
            case LTE -> field + " <= " + quoteValue(values, 0);
            case LIKE -> field + " LIKE '%" + escapeLike(values, 0) + "%'";
            case NOT_LIKE -> field + " NOT LIKE '%" + escapeLike(values, 0) + "%'";
            case IN -> field + " IN (" + quoteValues(values) + ")";
            case NOT_IN -> field + " NOT IN (" + quoteValues(values) + ")";
            case IS_NULL -> field + " IS NULL";
            case IS_NOT_NULL -> field + " IS NOT NULL";
            case BETWEEN -> {
                if (values == null || values.size() < 2) {
                    yield null;
                }
                yield field + " BETWEEN " + quoteValue(values, 0) + " AND " + quoteValue(values, 1);
            }
        };
    }

    /**
     * 给值加引号（数字不加）
     */
    private String quoteValue(List<String> values, int index) {
        if (values == null || values.size() <= index) {
            return "NULL";
        }
        String value = values.get(index);
        if (value == null) {
            return "NULL";
        }
        if (isNumber(value)) {
            return value;
        }
        return "'" + value.replace("'", "''") + "'";
    }

    /**
     * 批量给值加引号
     */
    private String quoteValues(List<String> values) {
        if (values == null || values.isEmpty()) {
            return "NULL";
        }
        return values.stream()
                .map(v -> isNumber(v) ? v : "'" + v.replace("'", "''") + "'")
                .collect(Collectors.joining(", "));
    }

    /**
     * 转义LIKE中的特殊字符
     */
    private String escapeLike(List<String> values, int index) {
        if (values == null || values.size() <= index) {
            return "";
        }
        return values.get(index).replace("'", "''");
    }

    /**
     * 判断是否为数字
     */
    private boolean isNumber(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 构建GROUP BY列
     */
    private List<String> buildGroupByColumns(AnalysisCmd cmd) {
        return Optional.ofNullable(cmd.getDimensions()).orElse(List.of())
                .stream()
                .map(DimensionConfigValObj::getField)
                .collect(Collectors.toList());
    }

    /**
     * 构建ORDER BY列
     */
    private List<String> buildOrderByColumns(AnalysisCmd cmd) {
        return Optional.ofNullable(cmd.getOrders()).orElse(List.of())
                .stream()
                .map(order -> order.getField() + " " + order.getDirection().getCode())
                .collect(Collectors.toList());
    }
}
