package com.cyan.databi.application.chart;

import com.cyan.arch.common.api.Page;
import com.cyan.databi.application.chart.bo.ChartBO;
import com.cyan.databi.application.chart.cmd.ChartCmd;
import com.cyan.databi.domain.chart.query.ChartListQuery;
import com.cyan.databi.domain.chart.query.ChartPageQuery;

import java.util.List;

/**
 * 图表应用服务接口
 *
 * @author cy.Y
 * @since 1.0.0
 */
public interface ChartService {

    /**
     * 分页查询图表
     */
    Page<ChartBO> page(ChartPageQuery query);

    /**
     * 列表查询图表
     */
    List<ChartBO> list(ChartListQuery query);

    /**
     * 根据ID查询图表
     */
    ChartBO findById(String id);

    /**
     * 保存图表
     */
    ChartBO save(ChartCmd cmd, String createdBy);

    /**
     * 更新图表
     */
    ChartBO update(String id, ChartCmd cmd);

    /**
     * 删除图表
     */
    void delete(String id);
}
