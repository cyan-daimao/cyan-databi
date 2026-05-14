package com.cyan.databi.adapter.chart.http.dto;

import com.cyan.datametric.client.dto.MetricBiAnalysisCmd;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * 图表数据传输对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ChartDataDTO {

    /**
     * 执行状态
     */
    private String status;

    /**
     * 执行耗时（毫秒）
     */
    private Long costTimeMs;

    /**
     * 列名列表
     */
    private List<String> columns;

    /**
     * 数据行列表
     */
    private List<Map<String, Object>> rows;

    /**
     * 生成的SQL
     */
    private String sql;

    /**
     * 图表类型
     */
    private String chartType;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * enriched DSL（包含 dimName/metricName）
     */
    private MetricBiAnalysisCmd dsl;
}
