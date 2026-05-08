package com.cyan.databi.application.chart.cmd;

import com.cyan.datametric.client.dto.MetricBiAnalysisCmd;
import com.cyan.datametric.client.dto.MetricBiAnalysisCmd.MetricRef;
import com.cyan.datametric.client.dto.MetricBiAnalysisCmd.DimensionRef;
import com.cyan.datametric.client.dto.MetricBiAnalysisCmd.FilterRef;
import com.cyan.datametric.client.dto.MetricBiAnalysisCmd.OrderRef;
import com.cyan.databi.domain.chart.valobj.DimensionConfigValObj;
import com.cyan.databi.domain.chart.valobj.MetricConfigValObj;
import com.cyan.databi.domain.chart.valobj.FilterConfigValObj;
import com.cyan.databi.domain.chart.valobj.OrderConfigValObj;
import com.cyan.databi.enums.AnalysisType;
import com.cyan.databi.enums.ChartType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

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
     * 关联数据集ID
     */
    private String datasetId;

    /**
     * 分析类型
     */
    private AnalysisType analysisType;

    /**
     * 指标分析DSL
     */
    private MetricBiAnalysisCmd metricAnalysisCmd;

    /**
     * 图表类型
     */
    @NotNull(message = "图表类型不能为空")
    private ChartType chartType;

    /**
     * 维度配置
     */
    private List<DimensionConfigValObj> dimensions;

    /**
     * 指标配置
     */
    private List<MetricConfigValObj> metrics;

    /**
     * 过滤配置
     */
    private List<FilterConfigValObj> filters;

    /**
     * 排序配置
     */
    private List<OrderConfigValObj> orders;

    /**
     * 限制条数
     */
    private Integer limitValue;

    /**
     * 生成的SQL内容
     */
    private String sqlContent;
}
