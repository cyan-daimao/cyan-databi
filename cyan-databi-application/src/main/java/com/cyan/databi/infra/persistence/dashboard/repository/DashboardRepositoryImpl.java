package com.cyan.databi.infra.persistence.dashboard.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyan.arch.common.api.Page;
import com.cyan.arch.common.util.StrUtils;
import com.cyan.databi.domain.dashboard.Dashboard;
import com.cyan.databi.domain.dashboard.query.DashboardListQuery;
import com.cyan.databi.domain.dashboard.query.DashboardPageQuery;
import com.cyan.databi.domain.dashboard.repository.DashboardRepository;
import com.cyan.databi.infra.persistence.dashboard.convert.DashboardInfraConvert;
import com.cyan.databi.infra.persistence.dashboard.dos.DashboardDO;
import com.cyan.databi.infra.persistence.dashboard.mappers.DashboardMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 看板仓储实现
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Repository
public class DashboardRepositoryImpl implements DashboardRepository {

    private final DashboardMapper dashboardMapper;

    public DashboardRepositoryImpl(DashboardMapper dashboardMapper) {
        this.dashboardMapper = dashboardMapper;
    }

    /**
     * 分页查询看板
     */
    @Override
    public Page<Dashboard> page(DashboardPageQuery query) {
        LambdaQueryWrapper<DashboardDO> wrapper = new LambdaQueryWrapper<DashboardDO>()
                .like(StrUtils.isNotBlank(query.getName()), DashboardDO::getName, query.getName())
                .eq(StrUtils.isNotBlank(query.getCreatedBy()), DashboardDO::getCreatedBy, query.getCreatedBy())
                .orderByDesc(DashboardDO::getCreatedAt);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DashboardDO> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(query.getCurrent(), query.getSize());
        page = dashboardMapper.selectPage(page, wrapper);
        List<Dashboard> data = Optional.ofNullable(page.getRecords()).orElse(List.of())
                .stream().map(DashboardInfraConvert.INSTANCE::toDashboard).toList();
        return new Page<>(data, page.getCurrent(), page.getSize(), page.getTotal());
    }

    /**
     * 列表查询看板
     */
    @Override
    public List<Dashboard> list(DashboardListQuery query) {
        LambdaQueryWrapper<DashboardDO> wrapper = new LambdaQueryWrapper<DashboardDO>()
                .like(StrUtils.isNotBlank(query.getName()), DashboardDO::getName, query.getName())
                .eq(StrUtils.isNotBlank(query.getCreatedBy()), DashboardDO::getCreatedBy, query.getCreatedBy())
                .orderByDesc(DashboardDO::getCreatedAt);
        List<DashboardDO> dos = dashboardMapper.selectList(wrapper);
        return Optional.ofNullable(dos).orElse(List.of())
                .stream().map(DashboardInfraConvert.INSTANCE::toDashboard).toList();
    }

    /**
     * 根据ID查询看板
     */
    @Override
    public Dashboard findById(String id) {
        DashboardDO dashboardDO = dashboardMapper.selectById(id);
        if (dashboardDO == null) {
            return null;
        }
        return DashboardInfraConvert.INSTANCE.toDashboard(dashboardDO);
    }

    /**
     * 保存看板
     */
    @Override
    public Dashboard save(Dashboard dashboard) {
        DashboardDO dashboardDO = DashboardInfraConvert.INSTANCE.toDashboardDO(dashboard);
        dashboardMapper.insert(dashboardDO);
        return findById(dashboardDO.getId() + "");
    }

    /**
     * 更新看板
     */
    @Override
    public Dashboard updateById(Dashboard dashboard) {
        DashboardDO dashboardDO = DashboardInfraConvert.INSTANCE.toDashboardDO(dashboard);
        dashboardMapper.updateById(dashboardDO);
        return findById(dashboard.getId());
    }

    /**
     * 删除看板
     */
    @Override
    public void deleteById(String id) {
        dashboardMapper.deleteById(id);
    }

    /**
     * 判断是否存在引用指定图表的看板
     */
    @Override
    public boolean existsByChartId(String chartId) {
        LambdaQueryWrapper<DashboardDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(DashboardDO::getChartRefs, "\"chartId\":\"" + chartId + "\"");
        return dashboardMapper.selectCount(wrapper) > 0;
    }
}
