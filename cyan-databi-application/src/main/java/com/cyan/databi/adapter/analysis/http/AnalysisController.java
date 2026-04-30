package com.cyan.databi.adapter.analysis.http;

import com.cyan.arch.common.api.Response;
import com.cyan.databi.adapter.analysis.http.convert.AnalysisAdapterConvert;
import com.cyan.databi.adapter.analysis.http.dto.ChartDataDTO;
import com.cyan.databi.application.analysis.AnalysisService;
import com.cyan.databi.application.analysis.bo.ChartDataBO;
import com.cyan.databi.application.analysis.cmd.AnalysisCmd;
import com.cyan.employee.login.holder.UserHolder;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 分析执行接口
 *
 * @author cy.Y
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    /**
     * 执行分析，生成SQL并查询数据
     */
    @PostMapping("/execute")
    public Response<ChartDataDTO> execute(@RequestBody @Valid AnalysisCmd cmd) {
        ChartDataBO bo = analysisService.execute(cmd, UserHolder.getUserCode());
        ChartDataDTO dto = AnalysisAdapterConvert.INSTANCE.toChartDataDTO(bo);
        return Response.success(dto);
    }

    /**
     * 预览SQL（不执行）
     */
    @PostMapping("/preview-sql")
    public Response<String> previewSql(@RequestBody @Valid AnalysisCmd cmd) {
        String sql = analysisService.previewSql(cmd);
        return Response.success(sql);
    }
}
