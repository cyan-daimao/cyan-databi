package com.cyan.databi.infra.client;

import com.cyan.arch.common.api.Response;
import com.cyan.databi.domain.chart.valobj.MetricBiAnalysisCmd;
import com.cyan.databi.infra.client.dto.MetricBiChartDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 指标分析客户端
 *
 * @author cy.Y
 * @since 1.0.0
 */
@FeignClient(name = "cyan-datametric", path = "/api/v1/metrics/bi/analysis")
public interface MetricBiAnalysisClient {

    /**
     * 执行指标分析
     *
     * @param cmd 指标分析命令
     * @return 图表数据
     */
    @PostMapping("/execute")
    Response<MetricBiChartDataDTO> execute(@RequestBody MetricBiAnalysisCmd cmd);

    /**
     * 预览指标分析SQL
     *
     * @param cmd 指标分析命令
     * @return 生成的SQL
     */
    @PostMapping("/preview-sql")
    Response<String> previewSql(@RequestBody MetricBiAnalysisCmd cmd);
}
