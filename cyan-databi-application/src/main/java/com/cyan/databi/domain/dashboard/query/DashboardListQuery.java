package com.cyan.databi.domain.dashboard.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 看板列表查询
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DashboardListQuery {

    /**
     * 看板名称（模糊查询）
     */
    private String name;

    /**
     * 创建人
     */
    private String createdBy;
}
