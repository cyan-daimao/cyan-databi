package com.cyan.databi.adapter.chart.http;

import com.cyan.arch.common.api.Page;
import com.cyan.arch.common.api.Response;
import com.cyan.databi.adapter.analysis.http.convert.AnalysisAdapterConvert;
import com.cyan.databi.adapter.analysis.http.dto.ChartDataDTO;
import com.cyan.databi.adapter.chart.http.convert.ChartAdapterConvert;
import com.cyan.databi.adapter.chart.http.dto.ChartDTO;
import com.cyan.databi.application.analysis.AnalysisService;
import com.cyan.databi.application.analysis.bo.ChartDataBO;
import com.cyan.databi.application.analysis.cmd.AnalysisCmd;
import com.cyan.databi.application.chart.ChartService;
import com.cyan.databi.application.chart.bo.ChartBO;
import com.cyan.databi.application.chart.cmd.ChartCmd;
import com.cyan.databi.domain.chart.query.ChartListQuery;
import com.cyan.databi.domain.chart.query.ChartPageQuery;
import com.cyan.employee.login.holder.UserHolder;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 图表接口
 *
 * @author cy.Y
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/charts")
public class ChartController {

    private final ChartService chartService;
    private final AnalysisService analysisService;

    public ChartController(ChartService chartService, AnalysisService analysisService) {
        this.chartService = chartService;
        this.analysisService = analysisService;
    }

    /**
     * 分页查询图表
     */
    @GetMapping
    public Response<Page<ChartDTO>> page(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) String datasetId,
                                          @RequestParam(required = false) Long current,
                                          @RequestParam(required = false) Long size) {
        current = current == null ? 1L : current;
        size = size == null ? 10L : size;
        ChartPageQuery query = new ChartPageQuery()
                .setName(name)
                .setDatasetId(datasetId)
                .setCreatedBy(UserHolder.getUserCode());
        query.setCurrent(current).setSize(size);
        Page<ChartBO> page = chartService.page(query);
        List<ChartDTO> data = Optional.ofNullable(page.getData()).orElse(List.of())
                .stream().map(ChartAdapterConvert.INSTANCE::toChartDTO).toList();
        Page<ChartDTO> result = new Page<>(data, page.getCurrent(), page.getSize(), page.getTotal());
        return Response.success(result);
    }

    /**
     * 列表查询图表
     */
    @GetMapping("/list")
    public Response<List<ChartDTO>> list(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) String datasetId) {
        ChartListQuery query = new ChartListQuery()
                .setName(name)
                .setDatasetId(datasetId)
                .setCreatedBy(UserHolder.getUserCode());
        List<ChartBO> bos = chartService.list(query);
        List<ChartDTO> dtos = Optional.ofNullable(bos).orElse(List.of())
                .stream().map(ChartAdapterConvert.INSTANCE::toChartDTO).toList();
        return Response.success(dtos);
    }

    /**
     * 根据ID查询图表
     */
    @GetMapping("/{id}")
    public Response<ChartDTO> findById(@PathVariable String id) {
        ChartBO bo = chartService.findById(id);
        ChartDTO dto = ChartAdapterConvert.INSTANCE.toChartDTO(bo);
        return Response.success(dto);
    }

    /**
     * 保存图表
     */
    @PostMapping
    public Response<ChartDTO> save(@RequestBody @Valid ChartCmd cmd) {
        ChartBO bo = chartService.save(cmd, UserHolder.getUserCode());
        ChartDTO dto = ChartAdapterConvert.INSTANCE.toChartDTO(bo);
        return Response.success(dto);
    }

    /**
     * 更新图表
     */
    @PutMapping("/{id}")
    public Response<ChartDTO> update(@PathVariable String id, @RequestBody @Valid ChartCmd cmd) {
        ChartBO bo = chartService.update(id, cmd);
        ChartDTO dto = ChartAdapterConvert.INSTANCE.toChartDTO(bo);
        return Response.success(dto);
    }

    /**
     * 删除图表
     */
    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable String id) {
        chartService.delete(id);
        return Response.success();
    }

    /**
     * 执行图表分析
     */
    @PostMapping("/{id}/execute")
    public Response<ChartDataDTO> execute(@PathVariable String id) {
        ChartBO chart = chartService.findById(id);
        AnalysisCmd cmd = new AnalysisCmd()
                .setDatasetId(chart.getDatasetId())
                .setChartType(chart.getChartType())
                .setDimensions(chart.getDimensions())
                .setMetrics(chart.getMetrics())
                .setFilters(chart.getFilters())
                .setOrders(chart.getOrders())
                .setLimitValue(chart.getLimitValue());
        ChartDataBO bo = analysisService.execute(cmd, UserHolder.getUserCode());
        ChartDataDTO dto = AnalysisAdapterConvert.INSTANCE.toChartDataDTO(bo);
        return Response.success(dto);
    }

    /**
     * 预览图表SQL
     */
    @GetMapping("/{id}/preview-sql")
    public Response<String> previewSql(@PathVariable String id) {
        ChartBO chart = chartService.findById(id);
        AnalysisCmd cmd = new AnalysisCmd()
                .setDatasetId(chart.getDatasetId())
                .setChartType(chart.getChartType())
                .setDimensions(chart.getDimensions())
                .setMetrics(chart.getMetrics())
                .setFilters(chart.getFilters())
                .setOrders(chart.getOrders())
                .setLimitValue(chart.getLimitValue());
        String sql = analysisService.previewSql(cmd);
        return Response.success(sql);
    }
}
