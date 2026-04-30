package com.cyan.databi.adapter.chart.http.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.databi.adapter.chart.http.dto.ChartDTO;
import com.cyan.databi.application.chart.bo.ChartBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 图表适配器层转换器
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper(uses = MapstructConvert.class)
public interface ChartAdapterConvert {

    ChartAdapterConvert INSTANCE = Mappers.getMapper(ChartAdapterConvert.class);

    /**
     * BO 转 DTO
     */
    ChartDTO toChartDTO(ChartBO chartBO);
}
