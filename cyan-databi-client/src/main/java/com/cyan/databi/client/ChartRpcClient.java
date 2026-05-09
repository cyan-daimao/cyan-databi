package com.cyan.databi.client;

import com.cyan.arch.common.api.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 图表 RPC 客户端（供内部服务调用 / Dify 工具调用，无登录拦截器）
 *
 * @author cy.Y
 * @since 1.0.0
 */
@FeignClient(name = "cyan-databi", path = "/rpc/v1/charts")
public interface ChartRpcClient {

    /**
     * 保存图表（ChatBI 调用）
     */
    @PostMapping
    Response<ChartSaveRpcResult> saveChart(@RequestBody ChartSaveRpcCmd cmd);
}
