package com.cyan.databi.client;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * ChatBI 保存图表 RPC 出参
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class ChartSaveRpcResult {

    /**
     * 图表ID
     */
    private String id;

    /**
     * 图表名称
     */
    private String name;
}
