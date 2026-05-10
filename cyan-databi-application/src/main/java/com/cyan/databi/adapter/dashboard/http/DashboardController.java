package com.cyan.databi.adapter.dashboard.http;

import com.cyan.arch.common.api.Page;
import com.cyan.arch.common.api.Response;
import com.cyan.databi.adapter.chart.http.convert.ChartAdapterConvert;
import com.cyan.databi.adapter.chart.http.dto.ChartDTO;
import com.cyan.databi.adapter.dashboard.http.convert.DashboardAdapterConvert;
import com.cyan.databi.adapter.dashboard.http.dto.DashboardChartRefDTO;
import com.cyan.databi.adapter.dashboard.http.dto.DashboardDTO;
import com.cyan.databi.application.chart.ChartService;
import com.cyan.databi.application.chart.bo.ChartBO;
import com.cyan.databi.application.dashboard.DashboardService;
import com.cyan.databi.application.dashboard.bo.DashboardBO;
import com.cyan.databi.application.dashboard.cmd.DashboardCmd;
import com.cyan.databi.domain.dashboard.query.DashboardListQuery;
import com.cyan.databi.domain.dashboard.query.DashboardPageQuery;
import com.cyan.databi.domain.dashboard.valobj.ChartRefValObj;
import com.cyan.employee.login.filter.UserContextHolder;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 看板接口
 *
 * @author cy.Y
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/dashboards")
public class DashboardController {

    private final DashboardService dashboardService;
    private final ChartService chartService;

    public DashboardController(DashboardService dashboardService, ChartService chartService) {
        this.dashboardService = dashboardService;
        this.chartService = chartService;
    }

    /**
     * 分页查询看板
     */
    @GetMapping
    public Response<Page<DashboardDTO>> page(@RequestParam(required = false) String name,
                                              @RequestParam(required = false) Long current,
                                              @RequestParam(required = false) Long size) {
        current = current == null ? 1L : current;
        size = size == null ? 10L : size;
        DashboardPageQuery query = new DashboardPageQuery()
                .setName(name)
                .setCreatedBy(UserContextHolder.getCurrentEmployee().getPassport());
        query.setCurrent(current).setSize(size);
        Page<DashboardBO> page = dashboardService.page(query);
        List<DashboardDTO> data = Optional.ofNullable(page.getData()).orElse(List.of())
                .stream().map(DashboardAdapterConvert.INSTANCE::toDashboardDTO).toList();
        Page<DashboardDTO> result = new Page<>(data, page.getCurrent(), page.getSize(), page.getTotal());
        return Response.success(result);
    }

    /**
     * 列表查询看板
     */
    @GetMapping("/list")
    public Response<List<DashboardDTO>> list(@RequestParam(required = false) String name) {
        DashboardListQuery query = new DashboardListQuery()
                .setName(name)
                .setCreatedBy(UserContextHolder.getCurrentEmployee().getPassport());
        List<DashboardBO> bos = dashboardService.list(query);
        List<DashboardDTO> dtos = Optional.ofNullable(bos).orElse(List.of())
                .stream().map(DashboardAdapterConvert.INSTANCE::toDashboardDTO).toList();
        return Response.success(dtos);
    }

    /**
     * 根据ID查询看板
     */
    @GetMapping("/{id}")
    public Response<DashboardDTO> findById(@PathVariable String id) {
        DashboardBO bo = dashboardService.findById(id);
        DashboardDTO dto = DashboardAdapterConvert.INSTANCE.toDashboardDTO(bo);
        return Response.success(dto);
    }

    /**
     * 查询看板内图表详情列表
     */
    @GetMapping("/{id}/charts")
    public Response<List<DashboardChartRefDTO>> findDashboardCharts(@PathVariable String id) {
        DashboardBO dashboard = dashboardService.findById(id);
        List<ChartRefValObj> chartRefs = Optional.ofNullable(dashboard.getChartRefs()).orElse(List.of());
        List<DashboardChartRefDTO> result = chartRefs.stream().map(ref -> {
            ChartBO chartBO = chartService.findById(ref.getChartId());
            ChartDTO chartDTO = ChartAdapterConvert.INSTANCE.toChartDTO(chartBO);
            return new DashboardChartRefDTO()
                    .setChartId(ref.getChartId())
                    .setX(ref.getX())
                    .setY(ref.getY())
                    .setW(ref.getW())
                    .setH(ref.getH())
                    .setTitleVisible(ref.getTitleVisible())
                    .setBorderStyle(ref.getBorderStyle())
                    .setBgColor(ref.getBgColor())
                    .setCascadeFrom(ref.getCascadeFrom())
                    .setChart(chartDTO);
        }).toList();
        return Response.success(result);
    }
    // TASK: done

    /**
     * 保存看板
     */
    @PostMapping
    public Response<DashboardDTO> save(@RequestBody @Valid DashboardCmd cmd) {
        DashboardBO bo = dashboardService.save(cmd, UserContextHolder.getCurrentEmployee().getPassport());
        DashboardDTO dto = DashboardAdapterConvert.INSTANCE.toDashboardDTO(bo);
        return Response.success(dto);
    }

    /**
     * 更新看板
     */
    @PutMapping("/{id}")
    public Response<DashboardDTO> update(@PathVariable String id, @RequestBody @Valid DashboardCmd cmd) {
        DashboardBO bo = dashboardService.update(id, cmd);
        DashboardDTO dto = DashboardAdapterConvert.INSTANCE.toDashboardDTO(bo);
        return Response.success(dto);
    }

    /**
     * 删除看板
     */
    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable String id) {
        dashboardService.delete(id);
        return Response.success();
    }
}
