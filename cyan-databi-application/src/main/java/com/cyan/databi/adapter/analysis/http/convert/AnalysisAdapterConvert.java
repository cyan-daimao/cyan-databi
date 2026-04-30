package com.cyan.databi.adapter.analysis.http.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.databi.adapter.analysis.http.dto.ChartDataDTO;
import com.cyan.databi.application.analysis.bo.ChartDataBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 分析适配器层转换器
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper(uses = MapstructConvert.class)
public interface AnalysisAdapterConvert {

    AnalysisAdapterConvert INSTANCE = Mappers.getMapper(AnalysisAdapterConvert.class);

    /**
     * BO 转 DTO
     */
    ChartDataDTO toChartDataDTO(ChartDataBO chartDataBO);
}
