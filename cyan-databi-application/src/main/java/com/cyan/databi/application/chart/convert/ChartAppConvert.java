package com.cyan.databi.application.chart.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.databi.application.chart.bo.ChartBO;
import com.cyan.databi.application.chart.cmd.ChartCmd;
import com.cyan.databi.domain.chart.Chart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 图表应用层转换器
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", uses = MapstructConvert.class)
public interface ChartAppConvert {

    ChartAppConvert INSTANCE = Mappers.getMapper(ChartAppConvert.class);

    /**
     * Domain 转 BO
     */
    ChartBO toChartBO(Chart chart);

    /**
     * Cmd 转 Domain
     */
    Chart toChart(ChartCmd cmd);
}
