package com.cyan.databi.infra.persistence.dashboard.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyan.databi.infra.persistence.dashboard.dos.DashboardDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 看板Mapper
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper
public interface DashboardMapper extends BaseMapper<DashboardDO> {
}
