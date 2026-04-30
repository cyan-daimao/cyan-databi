package com.cyan.databi.domain.chart.repository;

import com.cyan.arch.common.api.Page;
import com.cyan.databi.domain.chart.Chart;
import com.cyan.databi.domain.chart.query.ChartListQuery;
import com.cyan.databi.domain.chart.query.ChartPageQuery;

import java.util.List;

/**
 * 图表仓储接口
 *
 * @author cy.Y
 * @since 1.0.0
 */
public interface ChartRepository {

    /**
     * 分页查询图表
     */
    Page<Chart> page(ChartPageQuery query);

    /**
     * 列表查询图表
     */
    List<Chart> list(ChartListQuery query);

    /**
     * 根据ID查询图表
     */
    Chart findById(String id);

    /**
     * 保存图表
     */
    Chart save(Chart chart);

    /**
     * 更新图表
     */
    Chart updateById(Chart chart);

    /**
     * 删除图表
     */
    void deleteById(String id);
}
