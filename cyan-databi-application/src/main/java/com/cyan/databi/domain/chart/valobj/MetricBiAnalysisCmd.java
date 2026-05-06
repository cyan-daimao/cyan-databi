package com.cyan.databi.domain.chart.valobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 指标分析命令值对象（前端 DSL 结构）
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MetricBiAnalysisCmd {

    /**
     * 图表类型
     */
    private String chartType;

    /**
     * 指标列表
     */
    private List<MetricRef> metrics;

    /**
     * 维度列表
     */
    private List<DimensionRef> dimensions;

    /**
     * 过滤条件
     */
    private List<FilterRef> filters;

    /**
     * 排序配置
     */
    private List<OrderRef> orders;

    /**
     * 限制条数
     */
    private Integer limitValue;
}
