package com.cyan.databi.infra.persistence.chart.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.arch.common.util.JSON;
import com.cyan.databi.domain.chart.Chart;
import com.cyan.databi.domain.chart.valobj.*;
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
    @Mapping(target = "dimensionConfig", source = "dimensions", qualifiedByName = "dimensionsToJson")
    @Mapping(target = "metricConfig", source = "metrics", qualifiedByName = "metricsToJson")
    @Mapping(target = "filterConfig", source = "filters", qualifiedByName = "filtersToJson")
    @Mapping(target = "orderConfig", source = "orders", qualifiedByName = "ordersToJson")
    ChartDO toChartDO(Chart chart);

    @Named("jsonToDimensions")
    default List<DimensionConfigValObj> jsonToDimensions(String json) {
        if (json == null || json.isEmpty()) return List.of();
        try { return OBJECT_MAPPER.readValue(json, new TypeReference<List<DimensionConfigValObj>>() {}); }
        catch (Exception e) { throw new RuntimeException("维度配置JSON解析失败", e); }
    }

    @Named("dimensionsToJson")
    default String dimensionsToJson(List<DimensionConfigValObj> list) {
        if (list == null || list.isEmpty()) return null;
        return JSON.toJSONString(list);
    }

    @Named("jsonToMetrics")
    default List<MetricConfigValObj> jsonToMetrics(String json) {
        if (json == null || json.isEmpty()) return List.of();
        try { return OBJECT_MAPPER.readValue(json, new TypeReference<List<MetricConfigValObj>>() {}); }
        catch (Exception e) { throw new RuntimeException("指标配置JSON解析失败", e); }
    }

    @Named("metricsToJson")
    default String metricsToJson(List<MetricConfigValObj> list) {
        if (list == null || list.isEmpty()) return null;
        return JSON.toJSONString(list);
    }

    @Named("jsonToFilters")
    default List<FilterConfigValObj> jsonToFilters(String json) {
        if (json == null || json.isEmpty()) return List.of();
        try { return OBJECT_MAPPER.readValue(json, new TypeReference<List<FilterConfigValObj>>() {}); }
        catch (Exception e) { throw new RuntimeException("过滤配置JSON解析失败", e); }
    }

    @Named("filtersToJson")
    default String filtersToJson(List<FilterConfigValObj> list) {
        if (list == null || list.isEmpty()) return null;
        return JSON.toJSONString(list);
    }

    @Named("jsonToOrders")
    default List<OrderConfigValObj> jsonToOrders(String json) {
        if (json == null || json.isEmpty()) return List.of();
        try { return OBJECT_MAPPER.readValue(json, new TypeReference<List<OrderConfigValObj>>() {}); }
        catch (Exception e) { throw new RuntimeException("排序配置JSON解析失败", e); }
    }

    @Named("ordersToJson")
    default String ordersToJson(List<OrderConfigValObj> list) {
        if (list == null || list.isEmpty()) return null;
        return JSON.toJSONString(list);
    }
}
