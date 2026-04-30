package com.cyan.databi.domain.dataset.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 数据集列表查询
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DatasetListQuery {

    /**
     * 数据集名称（模糊查询）
     */
    private String name;

    /**
     * 创建人
     */
    private String createdBy;
}
