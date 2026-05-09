package com.cyan.databi.adapter.chart.http.dto;

import com.cyan.databi.domain.chart.valobj.*;
import com.cyan.databi.enums.AnalysisType;
import com.cyan.databi.enums.ChartType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

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
public class ChartDTO {

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

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedAt;
}
