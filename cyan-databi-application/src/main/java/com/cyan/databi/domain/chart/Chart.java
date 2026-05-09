package com.cyan.databi.domain.chart;

import com.cyan.arch.common.api.Assert;
import com.cyan.arch.common.api.SilentException;
import com.cyan.databi.domain.chart.repository.ChartRepository;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 图表领域对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Chart {

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
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;

    /**
     * 获取实际分析类型（兼容存量数据）
     */
    public AnalysisType getActualAnalysisType() {
        return this.analysisType != null ? this.analysisType : AnalysisType.DATASET;
    }

    /**
     * 保存图表
     */
    public Chart save(ChartRepository repository) {
        Assert.isBlank(this.id, new SilentException("新增时id必须为空"));
        Assert.notBlank(this.name, new SilentException("图表名称不能为空"));
        Assert.notNull(this.chartType, new SilentException("图表类型不能为空"));

        AnalysisType type = getActualAnalysisType();
        if (type == AnalysisType.DATASET) {
            Assert.notBlank(this.datasetId, new SilentException("数据集ID不能为空"));
            Assert.isNull(this.metricAnalysisCmd, new SilentException("DATASET类型图表指标分析配置必须为null"));
        } else {
            Assert.notNull(this.metricAnalysisCmd, new SilentException("指标分析配置不能为空"));
            Assert.isBlank(this.datasetId, new SilentException("METRICS类型图表数据集ID必须为null"));
        }

        return repository.save(this);
    }

    /**
     * 更新图表
     */
    public Chart update(ChartRepository repository) {
        Assert.notBlank(this.id, new SilentException("更新时id不能为空"));
        Assert.notBlank(this.name, new SilentException("图表名称不能为空"));

        AnalysisType type = getActualAnalysisType();
        if (type == AnalysisType.DATASET) {
            Assert.notBlank(this.datasetId, new SilentException("数据集ID不能为空"));
            Assert.isNull(this.metricAnalysisCmd, new SilentException("DATASET类型图表指标分析配置必须为null"));
        } else {
            Assert.notNull(this.metricAnalysisCmd, new SilentException("指标分析配置不能为空"));
            Assert.isBlank(this.datasetId, new SilentException("METRICS类型图表数据集ID必须为null"));
        }

        return repository.updateById(this);
    }

    /**
     * 删除图表
     */
    public void delete(ChartRepository repository) {
        Assert.notBlank(this.id, new SilentException("删除时id不能为空"));
        repository.deleteById(this.id);
    }
}
