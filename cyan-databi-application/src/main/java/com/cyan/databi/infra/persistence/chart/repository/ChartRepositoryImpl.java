package com.cyan.databi.infra.persistence.chart.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyan.arch.common.api.Page;
import com.cyan.arch.common.util.StrUtils;
import com.cyan.databi.domain.chart.Chart;
import com.cyan.databi.domain.chart.query.ChartListQuery;
import com.cyan.databi.domain.chart.query.ChartPageQuery;
import com.cyan.databi.domain.chart.repository.ChartRepository;
import com.cyan.databi.infra.persistence.chart.convert.ChartInfraConvert;
import com.cyan.databi.infra.persistence.chart.dos.ChartDO;
import com.cyan.databi.infra.persistence.chart.mappers.ChartMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 图表仓储实现
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Repository
public class ChartRepositoryImpl implements ChartRepository {

    private final ChartMapper chartMapper;

    public ChartRepositoryImpl(ChartMapper chartMapper) {
        this.chartMapper = chartMapper;
    }

    /**
     * 分页查询图表
     */
    @Override
    public Page<Chart> page(ChartPageQuery query) {
        LambdaQueryWrapper<ChartDO> wrapper = buildQueryWrapper(query);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ChartDO> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(query.getCurrent(), query.getSize());
        page = chartMapper.selectPage(page, wrapper);
        List<Chart> data = Optional.ofNullable(page.getRecords()).orElse(List.of())
                .stream().map(ChartInfraConvert.INSTANCE::toChart).toList();
        return new Page<>(data, page.getCurrent(), page.getSize(), page.getTotal());
    }

    /**
     * 列表查询图表
     */
    @Override
    public List<Chart> list(ChartListQuery query) {
        LambdaQueryWrapper<ChartDO> wrapper = new LambdaQueryWrapper<ChartDO>()
                .like(StrUtils.isNotBlank(query.getName()), ChartDO::getName, query.getName())
                .eq(StrUtils.isNotBlank(query.getDatasetId()), ChartDO::getDatasetId, com.cyan.arch.common.util.Convert.toLong(query.getDatasetId()))
                .eq(StrUtils.isNotBlank(query.getChartType()), ChartDO::getChartType,
                        StrUtils.isNotBlank(query.getChartType()) ? com.cyan.databi.enums.ChartType.valueOf(query.getChartType()) : null)
                .eq(StrUtils.isNotBlank(query.getCreatedBy()), ChartDO::getCreatedBy, query.getCreatedBy())
                .orderByDesc(ChartDO::getCreatedAt);
        List<ChartDO> dos = chartMapper.selectList(wrapper);
        return Optional.ofNullable(dos).orElse(List.of())
                .stream().map(ChartInfraConvert.INSTANCE::toChart).toList();
    }

    /**
     * 根据ID查询图表
     */
    @Override
    public Chart findById(String id) {
        ChartDO chartDO = chartMapper.selectById(id);
        if (chartDO == null) {
            return null;
        }
        return ChartInfraConvert.INSTANCE.toChart(chartDO);
    }

    /**
     * 保存图表
     */
    @Override
    public Chart save(Chart chart) {
        ChartDO chartDO = ChartInfraConvert.INSTANCE.toChartDO(chart);
        chartMapper.insert(chartDO);
        return findById(chartDO.getId() + "");
    }

    /**
     * 更新图表
     */
    @Override
    public Chart updateById(Chart chart) {
        ChartDO chartDO = ChartInfraConvert.INSTANCE.toChartDO(chart);
        chartMapper.updateById(chartDO);
        return findById(chart.getId());
    }

    /**
     * 删除图表
     */
    @Override
    public void deleteById(String id) {
        chartMapper.deleteById(id);
    }

    /**
     * 构建查询条件
     */
    private LambdaQueryWrapper<ChartDO> buildQueryWrapper(ChartPageQuery query) {
        return new LambdaQueryWrapper<ChartDO>()
                .like(StrUtils.isNotBlank(query.getName()), ChartDO::getName, query.getName())
                .eq(StrUtils.isNotBlank(query.getDatasetId()), ChartDO::getDatasetId, com.cyan.arch.common.util.Convert.toLong(query.getDatasetId()))
                .eq(StrUtils.isNotBlank(query.getChartType()), ChartDO::getChartType,
                        StrUtils.isNotBlank(query.getChartType()) ? com.cyan.databi.enums.ChartType.valueOf(query.getChartType()) : null)
                .eq(StrUtils.isNotBlank(query.getCreatedBy()), ChartDO::getCreatedBy, query.getCreatedBy())
                .orderByDesc(ChartDO::getCreatedAt);
    }
}
