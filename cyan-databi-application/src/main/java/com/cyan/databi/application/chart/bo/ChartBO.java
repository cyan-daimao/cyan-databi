package com.cyan.databi.application.chart.bo;

import com.cyan.datametric.client.dto.MetricBiAnalysisCmd;
import com.cyan.databi.enums.ChartType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 图表业务对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ChartBO {

    /**
     * 主键
     */
    private String id;

    /**
     * 图表名称
     */
    private String name;

    /**
     * 图表描述
     */
    private String description;

    /**
     * 指标分析DSL
     */
    private MetricBiAnalysisCmd metricAnalysisCmd;

    /**
     * 图表类型
     */
    private ChartType chartType;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    private LocalDateTime updatedAt;
}
