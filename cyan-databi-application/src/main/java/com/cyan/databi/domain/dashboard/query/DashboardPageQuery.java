package com.cyan.databi.domain.dashboard.query;

import com.cyan.arch.common.api.Pagination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 看板分页查询
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DashboardPageQuery extends Pagination {

    /**
     * 看板名称（模糊查询）
     */
    private String name;

    /**
     * 创建人
     */
    private String createdBy;
}
