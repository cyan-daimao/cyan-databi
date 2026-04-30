package com.cyan.databi.domain.dashboard.valobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 看板图表引用值对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ChartRefValObj {

    /**
     * 图表ID
     */
    private String chartId;

    /**
     * 横向位置
     */
    private Integer x;

    /**
     * 纵向位置
     */
    private Integer y;

    /**
     * 宽度
     */
    private Integer w;

    /**
     * 高度
     */
    private Integer h;
}
