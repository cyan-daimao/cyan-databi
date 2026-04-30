package com.cyan.databi.adapter.dataset.http.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.databi.adapter.dataset.http.dto.DatasetDTO;
import com.cyan.databi.application.dataset.bo.DatasetBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 数据集适配器层转换器
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper(uses = MapstructConvert.class)
public interface DatasetAdapterConvert {

    DatasetAdapterConvert INSTANCE = Mappers.getMapper(DatasetAdapterConvert.class);

    /**
     * BO 转 DTO
     */
    DatasetDTO toDatasetDTO(DatasetBO datasetBO);
}
