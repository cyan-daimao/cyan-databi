package com.cyan.databi.application.dashboard.impl;

import com.cyan.arch.common.api.Assert;
import com.cyan.arch.common.api.Page;
import com.cyan.arch.common.api.SilentException;
import com.cyan.databi.application.dashboard.DashboardService;
import com.cyan.databi.application.dashboard.bo.DashboardBO;
import com.cyan.databi.application.dashboard.cmd.DashboardCmd;
import com.cyan.databi.application.dashboard.convert.DashboardAppConvert;
import com.cyan.databi.domain.dashboard.Dashboard;
import com.cyan.databi.domain.dashboard.query.DashboardListQuery;
import com.cyan.databi.domain.dashboard.query.DashboardPageQuery;
import com.cyan.databi.domain.dashboard.repository.DashboardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 看板应用服务实现
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    private final DashboardRepository dashboardRepository;

    public DashboardServiceImpl(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    /**
     * 分页查询看板
     */
    @Override
    public Page<DashboardBO> page(DashboardPageQuery query) {
        Page<Dashboard> page = dashboardRepository.page(query);
        List<DashboardBO> data = Optional.ofNullable(page.getData()).orElse(List.of())
                .stream().map(DashboardAppConvert.INSTANCE::toDashboardBO).toList();
        return new Page<>(data, page.getCurrent(), page.getSize(), page.getTotal());
    }

    /**
     * 列表查询看板
     */
    @Override
    public List<DashboardBO> list(DashboardListQuery query) {
        List<Dashboard> dashboards = dashboardRepository.list(query);
        return Optional.ofNullable(dashboards).orElse(List.of())
                .stream().map(DashboardAppConvert.INSTANCE::toDashboardBO).toList();
    }

    /**
     * 根据ID查询看板
     */
    @Override
    public DashboardBO findById(String id) {
        Dashboard dashboard = dashboardRepository.findById(id);
        Assert.notNull(dashboard, new SilentException("看板不存在"));
        return DashboardAppConvert.INSTANCE.toDashboardBO(dashboard);
    }

    /**
     * 保存看板
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DashboardBO save(DashboardCmd cmd, String createdBy) {
        Dashboard dashboard = DashboardAppConvert.INSTANCE.toDashboard(cmd);
        dashboard.setCreatedBy(createdBy);
        dashboard = dashboard.save(dashboardRepository);
        return DashboardAppConvert.INSTANCE.toDashboardBO(dashboard);
    }

    /**
     * 更新看板
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DashboardBO update(String id, DashboardCmd cmd) {
        Dashboard existing = dashboardRepository.findById(id);
        Assert.notNull(existing, new SilentException("看板不存在"));
        Dashboard dashboard = DashboardAppConvert.INSTANCE.toDashboard(cmd);
        dashboard.setId(id);
        dashboard.setCreatedBy(existing.getCreatedBy());
        dashboard = dashboard.update(dashboardRepository);
        return DashboardAppConvert.INSTANCE.toDashboardBO(dashboard);
    }

    /**
     * 删除看板
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        Dashboard existing = dashboardRepository.findById(id);
        Assert.notNull(existing, new SilentException("看板不存在"));
        existing.delete(dashboardRepository);
    }
}
