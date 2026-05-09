package com.cyan.databi.infra.rpc;

import com.cyan.arch.common.api.Response;
import com.cyan.databi.adapter.chart.http.convert.ChartAdapterConvert;
import com.cyan.databi.application.chart.ChartService;
import com.cyan.databi.application.chart.bo.ChartBO;
import com.cyan.databi.application.chart.cmd.ChartCmd;
import com.cyan.databi.client.ChartRpcClient;
import com.cyan.databi.client.ChartSaveRpcCmd;
import com.cyan.databi.client.ChartSaveRpcResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图表 RPC 服务（供内部服务调用 / Dify 工具调用，无登录拦截器）
 *
 * @author cy.Y
 * @since 1.0.0
 */
@RestController
@RequestMapping("/rpc/v1/charts")
@RequiredArgsConstructor
public class ChartRpcController implements ChartRpcClient {

    private final ChartService chartService;

    @Override
    @PostMapping
    public Response<ChartSaveRpcResult> saveChart(@RequestBody ChartSaveRpcCmd cmd) {
        ChartCmd chartCmd = ChartAdapterConvert.INSTANCE.toChartCmd(cmd);
        ChartBO bo = chartService.save(chartCmd, "chatbi");
        ChartSaveRpcResult result = new ChartSaveRpcResult()
                .setId(bo.getId())
                .setName(bo.getName());
        return Response.success(result);
    }
}
