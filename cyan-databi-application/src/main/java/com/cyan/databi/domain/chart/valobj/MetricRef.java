package com.cyan.databi.domain.chart.valobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 指标引用值对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MetricRef {

    /**
     * 指标平台指标ID
     */
    private String metricId;

    /**
     * 前端显示别名
     */
    private String alias;
}
