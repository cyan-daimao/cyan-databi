package com.cyan.databi.application.chart.impl;

import com.cyan.arch.common.api.Assert;
import com.cyan.arch.common.api.Page;
import com.cyan.arch.common.api.SilentException;
import com.cyan.databi.application.chart.ChartService;
import com.cyan.databi.application.chart.bo.ChartBO;
import com.cyan.databi.application.chart.cmd.ChartCmd;
import com.cyan.databi.application.chart.convert.ChartAppConvert;
import com.cyan.databi.domain.chart.Chart;
import com.cyan.databi.domain.chart.query.ChartListQuery;
import com.cyan.databi.domain.chart.query.ChartPageQuery;
import com.cyan.databi.domain.chart.repository.ChartRepository;
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

    public ChartServiceImpl(ChartRepository chartRepository) {
        this.chartRepository = chartRepository;
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
        existing.delete(chartRepository);
    }
}
