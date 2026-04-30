package com.cyan.databi.domain.chart.query;

import com.cyan.arch.common.api.PageQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 图表分页查询
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ChartPageQuery extends PageQuery {

    /**
     * 图表名称（模糊查询）
     */
    private String name;

    /**
     * 数据集ID
     */
    private String datasetId;

    /**
     * 创建人
     */
    private String createdBy;
}
