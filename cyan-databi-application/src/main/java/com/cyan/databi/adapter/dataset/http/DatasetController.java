package com.cyan.databi.adapter.dataset.http;

import com.cyan.arch.common.api.Page;
import com.cyan.arch.common.api.Response;
import com.cyan.databi.adapter.dataset.http.convert.DatasetAdapterConvert;
import com.cyan.databi.adapter.dataset.http.dto.DatasetDTO;
import com.cyan.databi.application.dataset.DatasetService;
import com.cyan.databi.application.dataset.bo.DatasetBO;
import com.cyan.databi.application.dataset.cmd.DatasetCmd;
import com.cyan.databi.domain.dataset.query.DatasetListQuery;
import com.cyan.databi.domain.dataset.query.DatasetPageQuery;
import com.cyan.employee.login.filter.UserContextHolder;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 数据集接口
 *
 * @author cy.Y
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/datasets")
public class DatasetController {

    private final DatasetService datasetService;

    public DatasetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    /**
     * 分页查询数据集
     */
    @GetMapping
    public Response<Page<DatasetDTO>> page(@RequestParam(required = false) String name,
                                            @RequestParam(required = false) Long current,
                                            @RequestParam(required = false) Long size) {
        current = current == null ? 1L : current;
        size = size == null ? 10L : size;
        DatasetPageQuery query = new DatasetPageQuery()
                .setName(name)
                .setCreatedBy(UserContextHolder.getCurrentEmployee().getPassport());
        query.setCurrent(current).setSize(size);
        Page<DatasetBO> page = datasetService.page(query);
        List<DatasetDTO> data = Optional.ofNullable(page.getData()).orElse(List.of())
                .stream().map(DatasetAdapterConvert.INSTANCE::toDatasetDTO).toList();
        Page<DatasetDTO> result = new Page<>(data, page.getCurrent(), page.getSize(), page.getTotal());
        return Response.success(result);
    }

    /**
     * 列表查询数据集
     */
    @GetMapping("/list")
    public Response<List<DatasetDTO>> list(@RequestParam(required = false) String name) {
        DatasetListQuery query = new DatasetListQuery()
                .setName(name)
                .setCreatedBy(UserContextHolder.getCurrentEmployee().getPassport());
        List<DatasetBO> bos = datasetService.list(query);
        List<DatasetDTO> dtos = Optional.ofNullable(bos).orElse(List.of())
                .stream().map(DatasetAdapterConvert.INSTANCE::toDatasetDTO).toList();
        return Response.success(dtos);
    }

    /**
     * 根据ID查询数据集
     */
    @GetMapping("/{id}")
    public Response<DatasetDTO> findById(@PathVariable String id) {
        DatasetBO bo = datasetService.findById(id);
        DatasetDTO dto = DatasetAdapterConvert.INSTANCE.toDatasetDTO(bo);
        return Response.success(dto);
    }

    /**
     * 保存数据集
     */
    @PostMapping
    public Response<DatasetDTO> save(@RequestBody @Valid DatasetCmd cmd) {
        DatasetBO bo = datasetService.save(cmd, UserContextHolder.getCurrentEmployee().getPassport());
        DatasetDTO dto = DatasetAdapterConvert.INSTANCE.toDatasetDTO(bo);
        return Response.success(dto);
    }

    /**
     * 更新数据集
     */
    @PutMapping("/{id}")
    public Response<DatasetDTO> update(@PathVariable String id, @RequestBody @Valid DatasetCmd cmd) {
        DatasetBO bo = datasetService.update(id, cmd);
        DatasetDTO dto = DatasetAdapterConvert.INSTANCE.toDatasetDTO(bo);
        return Response.success(dto);
    }

    /**
     * 删除数据集
     */
    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable String id) {
        datasetService.delete(id);
        return Response.success();
    }
}
