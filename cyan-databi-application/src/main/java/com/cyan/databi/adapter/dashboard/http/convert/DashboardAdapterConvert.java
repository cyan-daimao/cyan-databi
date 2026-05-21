package com.cyan.databi.adapter.dashboard.http.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.databi.adapter.dashboard.http.dto.DashboardDTO;
import com.cyan.databi.application.dashboard.bo.DashboardBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 看板适配器层转换器
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", uses = MapstructConvert.class)
public interface DashboardAdapterConvert {

    DashboardAdapterConvert INSTANCE = Mappers.getMapper(DashboardAdapterConvert.class);

    /**
     * BO 转 DTO
     */
    DashboardDTO toDashboardDTO(DashboardBO dashboardBO);
}
