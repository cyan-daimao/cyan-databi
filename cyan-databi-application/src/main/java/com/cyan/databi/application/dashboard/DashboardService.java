package com.cyan.databi.application.dashboard;

import com.cyan.arch.common.api.Page;
import com.cyan.databi.application.dashboard.bo.DashboardBO;
import com.cyan.databi.application.dashboard.cmd.DashboardCmd;
import com.cyan.databi.domain.dashboard.query.DashboardListQuery;
import com.cyan.databi.domain.dashboard.query.DashboardPageQuery;

import java.util.List;

/**
 * 看板应用服务接口
 *
 * @author cy.Y
 * @since 1.0.0
 */
public interface DashboardService {

    /**
     * 分页查询看板
     */
    Page<DashboardBO> page(DashboardPageQuery query);

    /**
     * 列表查询看板
     */
    List<DashboardBO> list(DashboardListQuery query);

    /**
     * 根据ID查询看板
     */
    DashboardBO findById(String id);

    /**
     * 保存看板
     */
    DashboardBO save(DashboardCmd cmd, String createdBy);

    /**
     * 更新看板
     */
    DashboardBO update(String id, DashboardCmd cmd);

    /**
     * 删除看板
     */
    void delete(String id);
}
