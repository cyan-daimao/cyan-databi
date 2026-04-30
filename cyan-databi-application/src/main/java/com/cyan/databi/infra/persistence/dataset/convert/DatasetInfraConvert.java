package com.cyan.databi.infra.persistence.dataset.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.arch.common.util.JSON;
import com.cyan.databi.domain.dataset.Dataset;
import com.cyan.databi.domain.dataset.valobj.DatasetFieldValObj;
import com.cyan.databi.infra.persistence.dataset.dos.DatasetDO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 数据集基础设施层转换器
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper(uses = MapstructConvert.class)
public interface DatasetInfraConvert {

    DatasetInfraConvert INSTANCE = Mappers.getMapper(DatasetInfraConvert.class);
    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * DO 转 Domain
     */
    @Mapping(target = "id", expression = "java(com.cyan.arch.common.util.Convert.toStr(datasetDO.getId()))")
    @Mapping(target = "fields", source = "fields", qualifiedByName = "jsonToFields")
    Dataset toDataset(DatasetDO datasetDO);

    /**
     * Domain 转 DO
     */
    @Mapping(target = "id", expression = "java(com.cyan.arch.common.util.Convert.toLong(dataset.getId()))")
    @Mapping(target = "fields", source = "fields", qualifiedByName = "fieldsToJson")
    DatasetDO toDatasetDO(Dataset dataset);

    /**
     * JSON字符串转字段列表
     */
    @Named("jsonToFields")
    default List<DatasetFieldValObj> jsonToFields(String json) {
        if (json == null || json.isEmpty()) {
            return List.of();
        }
        try {
            return OBJECT_MAPPER.readValue(json, new TypeReference<List<DatasetFieldValObj>>() {});
        } catch (Exception e) {
            throw new RuntimeException("字段JSON解析失败", e);
        }
    }

    /**
     * 字段列表转JSON字符串
     */
    @Named("fieldsToJson")
    default String fieldsToJson(List<DatasetFieldValObj> fields) {
        if (fields == null || fields.isEmpty()) {
            return null;
        }
        return JSON.toJSONString(fields);
    }
}
