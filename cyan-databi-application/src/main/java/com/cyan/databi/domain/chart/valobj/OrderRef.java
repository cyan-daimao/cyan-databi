package com.cyan.databi.domain.chart.valobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 排序配置引用值对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderRef {

    /**
     * 指标编码（可选）
     */
    private String metricCode;

    /**
     * 维度编码（可选）
     */
    private String dimCode;

    /**
     * 排序方向：ASC / DESC
     */
    private String direction;
}
