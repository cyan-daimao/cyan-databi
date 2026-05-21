package com.cyan.databi.infra.persistence.chart.convert;

import com.cyan.arch.base.mapstruct.MapstructConvert;
import com.cyan.arch.common.util.JSON;
import com.cyan.databi.domain.chart.Chart;
import com.cyan.datametric.client.dto.MetricBiAnalysisCmd;
import com.cyan.databi.infra.persistence.chart.dos.ChartDO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * 图表基础设施层转换器
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", uses = MapstructConvert.class)
public interface ChartInfraConvert {

    ChartInfraConvert INSTANCE = Mappers.getMapper(ChartInfraConvert.class);
    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * DO 转 Domain
     */
    @Mapping(target = "id", expression = "java(com.cyan.arch.common.util.Convert.toStr(chartDO.getId()))")
    @Mapping(target = "metricAnalysisCmd", source = "metricAnalysisCmd", qualifiedByName = "jsonToMetricAnalysisCmd")
    Chart toChart(ChartDO chartDO);

    /**
     * Domain 转 DO
     */
    @Mapping(target = "id", expression = "java(com.cyan.arch.common.util.Convert.toLong(chart.getId()))")
    @Mapping(target = "metricAnalysisCmd", source = "metricAnalysisCmd", qualifiedByName = "metricAnalysisCmdToJson")
    ChartDO toChartDO(Chart chart);

    @Named("jsonToMetricAnalysisCmd")
    default MetricBiAnalysisCmd jsonToMetricAnalysisCmd(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, new TypeReference<MetricBiAnalysisCmd>() {});
        } catch (Exception e) {
            throw new RuntimeException("指标分析配置JSON解析失败", e);
        }
    }

    @Named("metricAnalysisCmdToJson")
    default String metricAnalysisCmdToJson(MetricBiAnalysisCmd cmd) {
        if (cmd == null) {
            return null;
        }
        return JSON.toJSONString(cmd);
    }
}
