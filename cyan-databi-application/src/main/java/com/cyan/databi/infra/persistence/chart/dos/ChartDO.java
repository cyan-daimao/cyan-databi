package com.cyan.databi.infra.persistence.chart.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cyan.databi.enums.ChartType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 图表数据对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("bi_chart")
public class ChartDO {

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 图表名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 图表描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 关联数据集ID
     */
    @TableField(value = "dataset_id")
    private Long datasetId;

    /**
     * 分析类型
     */
    @TableField(value = "analysis_type")
    private String analysisType;

    /**
     * 指标分析DSL JSON
     */
    @TableField(value = "metric_analysis_cmd")
    private String metricAnalysisCmd;

    /**
     * 图表类型
     */
    @TableField(value = "chart_type")
    private ChartType chartType;

    /**
     * 维度配置 JSON
     */
    @TableField(value = "dimension_config")
    private String dimensionConfig;

    /**
     * 指标配置 JSON
     */
    @TableField(value = "metric_config")
    private String metricConfig;

    /**
     * 过滤配置 JSON
     */
    @TableField(value = "filter_config")
    private String filterConfig;

    /**
     * 排序配置 JSON
     */
    @TableField(value = "order_config")
    private String orderConfig;

    /**
     * 限制条数
     */
    @TableField(value = "limit_value")
    private Integer limitValue;

    /**
     * 生成的SQL内容
     */
    @TableField(value = "sql_content")
    private String sqlContent;

    /**
     * 创建人
     */
    @TableField(value = "created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    @TableField(value = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    @TableField(value = "deleted_at")
    @TableLogic(value = "null", delval = "now()")
    private LocalDateTime deletedAt;
}
