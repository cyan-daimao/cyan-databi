package com.cyan.databi.adapter.dashboard.http.dto;

import com.cyan.databi.adapter.chart.http.dto.ChartDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 看板图表引用详情DTO（含图表完整元数据）
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DashboardChartRefDTO {

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

    /**
     * 是否显示标题
     */
    private Boolean titleVisible;

    /**
     * 边框样式
     */
    private String borderStyle;

    /**
     * 背景色
     */
    private String bgColor;

    /**
     * 本图表受哪些图表的筛选值影响
     */
    private List<String> cascadeFrom;

    /**
     * 图表完整元数据
     */
    private ChartDTO chart;
}
