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
     * 指标ID（可选）
     */
    private String metricId;

    /**
     * 维度ID（可选）
     */
    private String dimId;

    /**
     * 排序方向：ASC / DESC
     */
    private String direction;
}
