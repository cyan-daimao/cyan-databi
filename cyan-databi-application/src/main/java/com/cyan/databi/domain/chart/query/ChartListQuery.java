package com.cyan.databi.domain.chart.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 图表列表查询
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ChartListQuery {

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
