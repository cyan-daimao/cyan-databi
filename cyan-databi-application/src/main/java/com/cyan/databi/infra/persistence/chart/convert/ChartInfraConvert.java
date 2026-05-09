package com.cyan.databi.infra.persistence.chart.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.arch.common.util.JSON;
import com.cyan.databi.domain.chart.Chart;
import com.cyan.datametric.client.dto.MetricBiAnalysisCmd;
import com.cyan.databi.enums.AnalysisType;
import com.cyan.databi.infra.persistence.chart.dos.ChartDO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 图表基础设施层转换器
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper(uses = MapstructConvert.class)
public interface ChartInfraConvert {

    ChartInfraConvert INSTANCE = Mappers.getMapper(ChartInfraConvert.class);
    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * DO 转 Domain
     */
    @Mapping(target = "id", expression = "java(com.cyan.arch.common.util.Convert.toStr(chartDO.getId()))")
    @Mapping(target = "datasetId", expression = "java(com.cyan.arch.common.util.Convert.toStr(chartDO.getDatasetId()))")
    @Mapping(target = "analysisType", source = "analysisType", qualifiedByName = "strToAnalysisType")
    @Mapping(target = "metricAnalysisCmd", source = "metricAnalysisCmd", qualifiedByName = "jsonToMetricAnalysisCmd")
    @Mapping(target = "dimensions", source = "dimensionConfig", qualifiedByName = "jsonToDimensions")
    @Mapping(target = "metrics", source = "metricConfig", qualifiedByName = "jsonToMetrics")
    @Mapping(target = "filters", source = "filterConfig", qualifiedByName = "jsonToFilters")
    @Mapping(target = "orders", source = "orderConfig", qualifiedByName = "jsonToOrders")
    Chart toChart(ChartDO chartDO);

    /**
     * Domain 转 DO
     */
    @Mapping(target = "id", expression = "java(com.cyan.arch.common.util.Convert.toLong(chart.getId()))")
    @Mapping(target = "datasetId", expression = "java(com.cyan.arch.common.util.Convert.toLong(chart.getDatasetId()))")
    @Mapping(target = "analysisType", source = "analysisType", qualifiedByName = "analysisTypeToStr")
    @Mapping(target = "metricAnalysisCmd", source = "metricAnalysisCmd", qualifiedByName = "metricAnalysisCmdToJson")
    @Mapping(target = "dimensionConfig", source = "dimensions", qualifiedByName = "dimensionsToJson")
    @Mapping(target = "metricConfig", source = "metrics", qualifiedByName = "metricsToJson")
    @Mapping(target = "filterConfig", source = "filters", qualifiedByName = "filtersToJson")
    @Mapping(target = "orderConfig", source = "orders", qualifiedByName = "ordersToJson")
    ChartDO toChartDO(Chart chart);

    @Named("strToAnalysisType")
    default AnalysisType strToAnalysisType(String str) {
        if (str == null || str.isEmpty()) {
            return AnalysisType.DATASET;
        }
        return AnalysisType.valueOf(str);
    }

    @Named("analysisTypeToStr")
    default String analysisTypeToStr(AnalysisType type) {
        if (type == null) {
            return null;
        }
        return type.name();
    }

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

    @Named("jsonToDimensions")
    default List<com.cyan.databi.domain.chart.valobj.DimensionConfigValObj> jsonToDimensions(String json) {
        if (json == null || json.isEmpty()) return List.of();
        try { return OBJECT_MAPPER.readValue(json, new TypeReference<List<com.cyan.databi.domain.chart.valobj.DimensionConfigValObj>>() {}); }
        catch (Exception e) { throw new RuntimeException("维度配置JSON解析失败", e); }
    }

    @Named("dimensionsToJson")
    default String dimensionsToJson(List<com.cyan.databi.domain.chart.valobj.DimensionConfigValObj> list) {
        if (list == null || list.isEmpty()) return null;
        return JSON.toJSONString(list);
    }

    @Named("jsonToMetrics")
    default List<com.cyan.databi.domain.chart.valobj.MetricConfigValObj> jsonToMetrics(String json) {
        if (json == null || json.isEmpty()) return List.of();
        try { return OBJECT_MAPPER.readValue(json, new TypeReference<List<com.cyan.databi.domain.chart.valobj.MetricConfigValObj>>() {}); }
        catch (Exception e) { throw new RuntimeException("指标配置JSON解析失败", e); }
    }

    @Named("metricsToJson")
    default String metricsToJson(List<com.cyan.databi.domain.chart.valobj.MetricConfigValObj> list) {
        if (list == null || list.isEmpty()) return null;
        return JSON.toJSONString(list);
    }

    @Named("jsonToFilters")
    default List<com.cyan.databi.domain.chart.valobj.FilterConfigValObj> jsonToFilters(String json) {
        if (json == null || json.isEmpty()) return List.of();
        try { return OBJECT_MAPPER.readValue(json, new TypeReference<List<com.cyan.databi.domain.chart.valobj.FilterConfigValObj>>() {}); }
        catch (Exception e) { throw new RuntimeException("过滤配置JSON解析失败", e); }
    }

    @Named("filtersToJson")
    default String filtersToJson(List<com.cyan.databi.domain.chart.valobj.FilterConfigValObj> list) {
        if (list == null || list.isEmpty()) return null;
        return JSON.toJSONString(list);
    }

    @Named("jsonToOrders")
    default List<com.cyan.databi.domain.chart.valobj.OrderConfigValObj> jsonToOrders(String json) {
        if (json == null || json.isEmpty()) return List.of();
        try { return OBJECT_MAPPER.readValue(json, new TypeReference<List<com.cyan.databi.domain.chart.valobj.OrderConfigValObj>>() {}); }
        catch (Exception e) { throw new RuntimeException("排序配置JSON解析失败", e); }
    }

    @Named("ordersToJson")
    default String ordersToJson(List<com.cyan.databi.domain.chart.valobj.OrderConfigValObj> list) {
        if (list == null || list.isEmpty()) return null;
        return JSON.toJSONString(list);
    }
}
