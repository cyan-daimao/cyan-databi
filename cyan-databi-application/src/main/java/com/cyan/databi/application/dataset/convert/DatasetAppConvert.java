package com.cyan.databi.application.dataset.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.databi.application.dataset.bo.DatasetBO;
import com.cyan.databi.application.dataset.cmd.DatasetCmd;
import com.cyan.databi.domain.dataset.Dataset;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 数据集应用层转换器
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper(uses = MapstructConvert.class)
public interface DatasetAppConvert {

    DatasetAppConvert INSTANCE = Mappers.getMapper(DatasetAppConvert.class);

    /**
     * Domain 转 BO
     */
    DatasetBO toDatasetBO(Dataset dataset);

    /**
     * Cmd 转 Domain
     */
    Dataset toDataset(DatasetCmd cmd);
}
