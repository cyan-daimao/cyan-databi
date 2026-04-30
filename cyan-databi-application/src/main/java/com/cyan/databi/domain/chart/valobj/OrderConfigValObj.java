package com.cyan.databi.domain.chart.valobj;

import com.cyan.databi.enums.SortDirection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 排序配置值对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderConfigValObj {

    /**
     * 字段名
     */
    private String field;

    /**
     * 排序方向
     */
    private SortDirection direction;
}
