package com.cyan.databi.client;

import com.cyan.datametric.client.dto.MetricBiAnalysisCmd;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * ChatBI 保存图表 RPC 入参
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class ChartSaveRpcCmd {

    /**
     * 图表名称
     */
    private String name;

    /**
     * 图表描述
     */
    private String description;

    /**
     * 图表类型
     */
    private String chartType;

    /**
     * 指标分析 DSL
     */
    private MetricBiAnalysisCmd metricAnalysisCmd;

    /**
     * 维度配置（前端展示用，可选）
     */
    private List<Map<String, Object>> dimensions;

    /**
     * 指标配置（前端展示用，可选）
     */
    private List<Map<String, Object>> metrics;

    /**
     * 过滤配置（可选）
     */
    private List<Map<String, Object>> filters;

    /**
     * 排序配置（可选）
     */
    private List<Map<String, Object>> orders;

    /**
     * 限制条数
     */
    private Integer limitValue;
}
