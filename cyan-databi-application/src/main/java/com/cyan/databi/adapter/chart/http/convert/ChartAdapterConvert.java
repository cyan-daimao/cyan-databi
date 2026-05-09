package com.cyan.databi.adapter.chart.http.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.databi.adapter.chart.http.dto.ChartDTO;
import com.cyan.databi.application.chart.bo.ChartBO;
import com.cyan.databi.application.chart.cmd.ChartCmd;
import com.cyan.databi.client.ChartSaveRpcCmd;
import com.cyan.databi.domain.chart.valobj.DimensionConfigValObj;
import com.cyan.databi.domain.chart.valobj.FilterConfigValObj;
import com.cyan.databi.domain.chart.valobj.MetricConfigValObj;
import com.cyan.databi.domain.chart.valobj.OrderConfigValObj;
import com.cyan.databi.enums.AggregateType;
import com.cyan.databi.enums.AnalysisType;
import com.cyan.databi.enums.ChartType;
import com.cyan.databi.enums.FilterOperator;
import com.cyan.databi.enums.SortDirection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

/**
 * 图表适配器层转换器
 * <p>
 * 负责 BO → DTO、RPC Cmd → Application Cmd 的转换
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper(uses = MapstructConvert.class)
public interface ChartAdapterConvert {

    ChartAdapterConvert INSTANCE = Mappers.getMapper(ChartAdapterConvert.class);

    /**
     * BO 转 DTO
     */
    ChartDTO toChartDTO(ChartBO chartBO);

    /**
     * RPC 保存图表入参转应用层命令对象
     */
    default ChartCmd toChartCmd(ChartSaveRpcCmd rpcCmd) {
        if (rpcCmd == null) {
            return null;
        }
        ChartCmd cmd = new ChartCmd();
        cmd.setName(rpcCmd.getName());
        cmd.setDescription(rpcCmd.getDescription());
        cmd.setAnalysisType(rpcCmd.getAnalysisType() != null ? AnalysisType.valueOf(rpcCmd.getAnalysisType()) : null);
        cmd.setChartType(rpcCmd.getChartType() != null ? ChartType.valueOf(rpcCmd.getChartType()) : null);
        cmd.setMetricAnalysisCmd(rpcCmd.getMetricAnalysisCmd());
        cmd.setLimitValue(rpcCmd.getLimitValue());

        if (rpcCmd.getDimensions() != null) {
            cmd.setDimensions(rpcCmd.getDimensions().stream()
                    .map(m -> new DimensionConfigValObj()
                            .setField(getString(m, "field"))
                            .setAlias(getString(m, "alias")))
                    .toList());
        }

        if (rpcCmd.getMetrics() != null) {
            cmd.setMetrics(rpcCmd.getMetrics().stream()
                    .map(m -> new MetricConfigValObj()
                            .setField(getString(m, "field"))
                            .setAggregate(getEnum(m, "aggregate", AggregateType.class))
                            .setAlias(getString(m, "alias")))
                    .toList());
        }

        if (rpcCmd.getFilters() != null) {
            cmd.setFilters(rpcCmd.getFilters().stream()
                    .map(m -> new FilterConfigValObj()
                            .setField(getString(m, "field"))
                            .setOperator(getEnum(m, "operator", FilterOperator.class))
                            .setValues(getStringList(m, "values")))
                    .toList());
        }

        if (rpcCmd.getOrders() != null) {
            cmd.setOrders(rpcCmd.getOrders().stream()
                    .map(m -> new OrderConfigValObj()
                            .setField(getString(m, "field"))
                            .setDirection(getEnum(m, "direction", SortDirection.class)))
                    .toList());
        }

        return cmd;
    }

    @SuppressWarnings("unchecked")
    private String getString(Map<String, Object> map, String key) {
        Object v = map.get(key);
        return v != null ? v.toString() : null;
    }

    @SuppressWarnings("unchecked")
    private <E extends Enum<E>> E getEnum(Map<String, Object> map, String key, Class<E> enumClass) {
        Object v = map.get(key);
        if (v == null) return null;
        try {
            return Enum.valueOf(enumClass, v.toString().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private List<String> getStringList(Map<String, Object> map, String key) {
        Object v = map.get(key);
        if (v instanceof List) {
            return ((List<?>) v).stream().map(Object::toString).toList();
        }
        return null;
    }
}
