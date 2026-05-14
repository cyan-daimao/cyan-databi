package com.cyan.databi.application.chart.cmd;

import com.cyan.datametric.client.dto.MetricBiAnalysisCmd;
import com.cyan.databi.enums.ChartType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * 图表命令对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ChartCmd {

    /**
     * 图表名称
     */
    @NotBlank(message = "图表名称不能为空")
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
    @NotNull(message = "图表类型不能为空")
    private ChartType chartType;
}
