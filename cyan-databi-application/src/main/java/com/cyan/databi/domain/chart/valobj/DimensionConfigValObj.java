package com.cyan.databi.domain.chart.valobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 维度配置值对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DimensionConfigValObj {

    /**
     * 字段名
     */
    private String field;

    /**
     * 字段别名（展示用）
     */
    private String alias;
}
