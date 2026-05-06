package com.cyan.databi.application.chart;

import com.cyan.arch.common.api.Page;
import com.cyan.databi.application.analysis.bo.ChartDataBO;
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

    /**
     * 执行图表分析
     *
     * @param chartId 图表ID
     * @param executor 执行人
     * @return 图表数据
     */
    ChartDataBO executeChart(String chartId, String executor);

    /**
     * 预览图表SQL
     *
     * @param chartId 图表ID
     * @return 生成的SQL
     */
    String previewChartSql(String chartId);
}
