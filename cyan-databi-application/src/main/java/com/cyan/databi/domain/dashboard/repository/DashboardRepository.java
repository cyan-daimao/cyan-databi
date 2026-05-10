package com.cyan.databi.domain.dashboard.repository;

import com.cyan.arch.common.api.Page;
import com.cyan.databi.domain.dashboard.Dashboard;
import com.cyan.databi.domain.dashboard.query.DashboardListQuery;
import com.cyan.databi.domain.dashboard.query.DashboardPageQuery;

import java.util.List;

/**
 * 看板仓储接口
 *
 * @author cy.Y
 * @since 1.0.0
 */
public interface DashboardRepository {

    /**
     * 分页查询看板
     */
    Page<Dashboard> page(DashboardPageQuery query);

    /**
     * 列表查询看板
     */
    List<Dashboard> list(DashboardListQuery query);

    /**
     * 根据ID查询看板
     */
    Dashboard findById(String id);

    /**
     * 保存看板
     */
    Dashboard save(Dashboard dashboard);

    /**
     * 更新看板
     */
    Dashboard updateById(Dashboard dashboard);

    /**
     * 删除看板
     */
    void deleteById(String id);

    /**
     * 判断是否存在引用指定图表的看板
     */
    boolean existsByChartId(String chartId);
}
