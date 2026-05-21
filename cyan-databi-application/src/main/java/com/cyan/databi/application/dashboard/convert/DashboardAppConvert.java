package com.cyan.databi.application.dashboard.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.databi.application.dashboard.bo.DashboardBO;
import com.cyan.databi.application.dashboard.cmd.DashboardCmd;
import com.cyan.databi.domain.dashboard.Dashboard;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 看板应用层转换器
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", uses = MapstructConvert.class)
public interface DashboardAppConvert {

    DashboardAppConvert INSTANCE = Mappers.getMapper(DashboardAppConvert.class);

    /**
     * Domain 转 BO
     */
    DashboardBO toDashboardBO(Dashboard dashboard);

    /**
     * Cmd 转 Domain
     */
    Dashboard toDashboard(DashboardCmd cmd);
}
