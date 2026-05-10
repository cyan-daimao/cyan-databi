package com.cyan.databi.application.chart.cmd;

import com.cyan.datametric.client.dto.MetricBiAnalysisCmd;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 图表执行命令对象（支持传入自定义DSL）
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ChartExecuteCmd {

    /**
     * 指标分析DSL（可选，传入时优先使用）
     */
    private MetricBiAnalysisCmd metricAnalysisCmd;
}
