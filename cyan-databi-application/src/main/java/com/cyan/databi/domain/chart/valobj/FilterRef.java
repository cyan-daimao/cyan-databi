package com.cyan.databi.domain.chart.valobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 过滤条件引用值对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class FilterRef {

    /**
     * 指标编码（可选，默认全局过滤）
     */
    private String metricCode;

    /**
     * 维度编码
     */
    private String dimCode;

    /**
     * 操作符
     */
    private String operator;

    /**
     * 过滤值列表
     */
    private List<String> values;
}
