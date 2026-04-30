package com.cyan.databi.infra.persistence.chart.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyan.databi.infra.persistence.chart.dos.ChartDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图表Mapper
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper
public interface ChartMapper extends BaseMapper<ChartDO> {
}
