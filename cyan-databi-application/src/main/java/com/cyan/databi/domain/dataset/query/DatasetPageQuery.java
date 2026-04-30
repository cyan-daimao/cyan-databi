package com.cyan.databi.domain.dataset.query;

import com.cyan.arch.common.api.Pagination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 数据集分页查询
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DatasetPageQuery extends Pagination {

    /**
     * 数据集名称（模糊查询）
     */
    private String name;

    /**
     * 创建人
     */
    private String createdBy;
}
