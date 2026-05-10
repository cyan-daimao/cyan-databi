package com.cyan.databi.infra.persistence.dashboard.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.arch.common.util.JSON;
import com.cyan.databi.domain.dashboard.Dashboard;
import com.cyan.databi.domain.dashboard.valobj.ChartRefValObj;
import com.cyan.databi.infra.persistence.dashboard.dos.DashboardDO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 看板基础设施层转换器
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper(uses = MapstructConvert.class)
public interface DashboardInfraConvert {

    DashboardInfraConvert INSTANCE = Mappers.getMapper(DashboardInfraConvert.class);
    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * DO 转 Domain
     */
    @Mapping(target = "id", expression = "java(com.cyan.arch.common.util.Convert.toStr(dashboardDO.getId()))")
    @Mapping(target = "chartRefs", source = "chartRefs", qualifiedByName = "jsonToChartRefs")
    Dashboard toDashboard(DashboardDO dashboardDO);

    /**
     * Domain 转 DO
     */
    @Mapping(target = "id", expression = "java(com.cyan.arch.common.util.Convert.toLong(dashboard.getId()))")
    @Mapping(target = "chartRefs", source = "chartRefs", qualifiedByName = "chartRefsToJson")
    DashboardDO toDashboardDO(Dashboard dashboard);

    @Named("jsonToChartRefs")
    default List<ChartRefValObj> jsonToChartRefs(String json) {
        if (json == null || json.isEmpty()) return List.of();
        try {
            List<ChartRefValObj> list = OBJECT_MAPPER.readValue(json, new TypeReference<List<ChartRefValObj>>() {});
            for (ChartRefValObj ref : list) {
                if (ref.getTitleVisible() == null) {
                    ref.setTitleVisible(true);
                }
                if (ref.getBorderStyle() == null) {
                    ref.setBorderStyle("default");
                }
                if (ref.getCascadeFrom() == null) {
                    ref.setCascadeFrom(List.of());
                }
            }
            return list;
        } catch (Exception e) { throw new RuntimeException("图表引用JSON解析失败", e); }
    }

    @Named("chartRefsToJson")
    default String chartRefsToJson(List<ChartRefValObj> list) {
        if (list == null || list.isEmpty()) return null;
        return JSON.toJSONString(list);
    }
}
// TASK: done
