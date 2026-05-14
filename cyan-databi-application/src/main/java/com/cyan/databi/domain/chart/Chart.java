package com.cyan.databi.domain.chart;

import com.cyan.arch.common.api.Assert;
import com.cyan.arch.common.api.SilentException;
import com.cyan.databi.domain.chart.repository.ChartRepository;
import com.cyan.datametric.client.dto.MetricBiAnalysisCmd;
import com.cyan.databi.enums.ChartType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

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

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;

    /**
     * 保存图表
     */
    public Chart save(ChartRepository repository) {
        Assert.isBlank(this.id, new SilentException("新增时id必须为空"));
        Assert.notBlank(this.name, new SilentException("图表名称不能为空"));
        Assert.notNull(this.chartType, new SilentException("图表类型不能为空"));
        Assert.notNull(this.metricAnalysisCmd, new SilentException("指标分析配置不能为空"));

        return repository.save(this);
    }

    /**
     * 更新图表
     */
    public Chart update(ChartRepository repository) {
        Assert.notBlank(this.id, new SilentException("更新时id不能为空"));
        Assert.notBlank(this.name, new SilentException("图表名称不能为空"));
        Assert.notNull(this.metricAnalysisCmd, new SilentException("指标分析配置不能为空"));

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
