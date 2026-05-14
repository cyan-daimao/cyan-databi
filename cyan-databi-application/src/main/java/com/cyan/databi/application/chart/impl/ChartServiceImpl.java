package com.cyan.databi.application.chart.impl;

import com.cyan.arch.common.api.Assert;
import com.cyan.arch.common.api.Page;
import com.cyan.arch.common.api.Response;
import com.cyan.arch.common.api.SilentException;
import com.cyan.databi.application.chart.ChartService;
import com.cyan.databi.application.chart.bo.ChartBO;
import com.cyan.databi.application.chart.bo.ChartDataBO;
import com.cyan.databi.application.chart.cmd.ChartCmd;
import com.cyan.databi.application.chart.cmd.ChartExecuteCmd;
import com.cyan.databi.application.chart.convert.ChartAppConvert;
import com.cyan.databi.domain.chart.Chart;
import com.cyan.databi.domain.chart.query.ChartListQuery;
import com.cyan.databi.domain.chart.query.ChartPageQuery;
import com.cyan.databi.domain.chart.repository.ChartRepository;
import com.cyan.databi.domain.dashboard.repository.DashboardRepository;
import com.cyan.datametric.client.MetricBiAnalysisClient;
import com.cyan.datametric.client.dto.MetricBiAnalysisCmd;
import com.cyan.datametric.client.dto.MetricBiChartDataDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 图表应用服务实现
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Service
public class ChartServiceImpl implements ChartService {

    private final ChartRepository chartRepository;
    private final MetricBiAnalysisClient metricBiAnalysisClient;
    private final DashboardRepository dashboardRepository;

    public ChartServiceImpl(ChartRepository chartRepository,
                            MetricBiAnalysisClient metricBiAnalysisClient,
                            DashboardRepository dashboardRepository) {
        this.chartRepository = chartRepository;
        this.metricBiAnalysisClient = metricBiAnalysisClient;
        this.dashboardRepository = dashboardRepository;
    }

    /**
     * 分页查询图表
     */
    @Override
    public Page<ChartBO> page(ChartPageQuery query) {
        Page<Chart> page = chartRepository.page(query);
        List<ChartBO> data = Optional.ofNullable(page.getData()).orElse(List.of())
                .stream().map(ChartAppConvert.INSTANCE::toChartBO).toList();
        return new Page<>(data, page.getCurrent(), page.getSize(), page.getTotal());
    }

    /**
     * 列表查询图表
     */
    @Override
    public List<ChartBO> list(ChartListQuery query) {
        List<Chart> charts = chartRepository.list(query);
        return Optional.ofNullable(charts).orElse(List.of())
                .stream().map(ChartAppConvert.INSTANCE::toChartBO).toList();
    }

    /**
     * 根据ID查询图表
     */
    @Override
    public ChartBO findById(String id) {
        Chart chart = chartRepository.findById(id);
        Assert.notNull(chart, new SilentException("图表不存在"));
        return ChartAppConvert.INSTANCE.toChartBO(chart);
    }

    /**
     * 保存图表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ChartBO save(ChartCmd cmd, String createdBy) {
        Chart chart = ChartAppConvert.INSTANCE.toChart(cmd);
        chart.setCreatedBy(createdBy);
        chart = chart.save(chartRepository);
        return ChartAppConvert.INSTANCE.toChartBO(chart);
    }

    /**
     * 更新图表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ChartBO update(String id, ChartCmd cmd) {
        Chart existing = chartRepository.findById(id);
        Assert.notNull(existing, new SilentException("图表不存在"));
        Chart chart = ChartAppConvert.INSTANCE.toChart(cmd);
        chart.setId(id);
        // 保留原创建人，防止被覆盖为空
        chart.setCreatedBy(existing.getCreatedBy());
        chart = chart.update(chartRepository);
        return ChartAppConvert.INSTANCE.toChartBO(chart);
    }

    /**
     * 删除图表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        Chart existing = chartRepository.findById(id);
        Assert.notNull(existing, new SilentException("图表不存在"));
        if (dashboardRepository.existsByChartId(id)) {
            throw new SilentException("该图表已被看板引用，无法删除");
        }
        existing.delete(chartRepository);
    }

    /**
     * 执行图表分析
     */
    @Override
    public ChartDataBO executeChart(String chartId, String executor) {
        return executeChart(chartId, executor, null);
    }

    /**
     * 执行图表分析（支持传入自定义DSL）
     */
    @Override
    public ChartDataBO executeChart(String chartId, String executor, ChartExecuteCmd executeCmd) {
        Chart chart = chartRepository.findById(chartId);
        Assert.notNull(chart, new SilentException("图表不存在"));

        MetricBiAnalysisCmd cmd = (executeCmd != null && executeCmd.getMetricAnalysisCmd() != null)
                ? executeCmd.getMetricAnalysisCmd()
                : chart.getMetricAnalysisCmd();
        Response<MetricBiChartDataDTO> response = metricBiAnalysisClient.execute(cmd);
        MetricBiChartDataDTO dto = response.getData();
        if (dto == null) {
            return new ChartDataBO()
                    .setStatus("FAILED")
                    .setErrorMessage(response.getMessage());
        }
        ChartDataBO result = new ChartDataBO()
                .setStatus(dto.getStatus())
                .setCostTimeMs(dto.getCostTimeMs())
                .setColumns(dto.getColumns())
                .setRows(dto.getRows())
                .setSql(dto.getSql())
                .setChartType(dto.getChartType())
                .setErrorMessage(dto.getErrorMessage());
        if (chart.getChartType() != null) {
            result.setChartType(chart.getChartType().name());
        }
        return result;
    }

    /**
     * 预览图表SQL
     */
    @Override
    public String previewChartSql(String chartId) {
        Chart chart = chartRepository.findById(chartId);
        Assert.notNull(chart, new SilentException("图表不存在"));

        Response<String> response = metricBiAnalysisClient.previewSql(chart.getMetricAnalysisCmd());
        return response.getData();
    }
}
