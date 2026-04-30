package com.cyan.databi.infra.persistence.dataset.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyan.databi.infra.persistence.dataset.dos.DatasetDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据集Mapper
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper
public interface DatasetMapper extends BaseMapper<DatasetDO> {
}
