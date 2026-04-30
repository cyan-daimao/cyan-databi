package com.cyan.databi.domain.chart.valobj;

import com.cyan.databi.enums.AggregateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 指标配置值对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MetricConfigValObj {

    /**
     * 字段名
     */
    private String field;

    /**
     * 聚合函数
     */
    private AggregateType aggregate;

    /**
     * 字段别名（展示用）
     */
    private String alias;
}
