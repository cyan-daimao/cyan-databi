package com.cyan.databi.application.dataset.impl;

import com.cyan.arch.common.api.Assert;
import com.cyan.arch.common.api.Page;
import com.cyan.arch.common.api.SilentException;
import com.cyan.databi.application.dataset.DatasetService;
import com.cyan.databi.application.dataset.bo.DatasetBO;
import com.cyan.databi.application.dataset.cmd.DatasetCmd;
import com.cyan.databi.application.dataset.convert.DatasetAppConvert;
import com.cyan.databi.domain.dataset.Dataset;
import com.cyan.databi.domain.dataset.query.DatasetListQuery;
import com.cyan.databi.domain.dataset.query.DatasetPageQuery;
import com.cyan.databi.domain.dataset.repository.DatasetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 数据集应用服务实现
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Service
public class DatasetServiceImpl implements DatasetService {

    private final DatasetRepository datasetRepository;

    public DatasetServiceImpl(DatasetRepository datasetRepository) {
        this.datasetRepository = datasetRepository;
    }

    /**
     * 分页查询数据集
     */
    @Override
    public Page<DatasetBO> page(DatasetPageQuery query) {
        Page<Dataset> page = datasetRepository.page(query);
        List<DatasetBO> data = Optional.ofNullable(page.getData()).orElse(List.of())
                .stream().map(DatasetAppConvert.INSTANCE::toDatasetBO).toList();
        return new Page<>(data, page.getCurrent(), page.getSize(), page.getTotal());
    }

    /**
     * 列表查询数据集
     */
    @Override
    public List<DatasetBO> list(DatasetListQuery query) {
        List<Dataset> datasets = datasetRepository.list(query);
        return Optional.ofNullable(datasets).orElse(List.of())
                .stream().map(DatasetAppConvert.INSTANCE::toDatasetBO).toList();
    }

    /**
     * 根据ID查询数据集
     */
    @Override
    public DatasetBO findById(String id) {
        Dataset dataset = datasetRepository.findById(id);
        Assert.notNull(dataset, new SilentException("数据集不存在"));
        return DatasetAppConvert.INSTANCE.toDatasetBO(dataset);
    }

    /**
     * 保存数据集
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DatasetBO save(DatasetCmd cmd, String createdBy) {
        Dataset dataset = DatasetAppConvert.INSTANCE.toDataset(cmd);
        dataset.setCreatedBy(createdBy);
        dataset = dataset.save(datasetRepository);
        return DatasetAppConvert.INSTANCE.toDatasetBO(dataset);
    }

    /**
     * 更新数据集
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DatasetBO update(String id, DatasetCmd cmd) {
        Dataset existing = datasetRepository.findById(id);
        Assert.notNull(existing, new SilentException("数据集不存在"));
        Dataset dataset = DatasetAppConvert.INSTANCE.toDataset(cmd);
        dataset.setId(id);
        dataset = dataset.update(datasetRepository);
        return DatasetAppConvert.INSTANCE.toDatasetBO(dataset);
    }

    /**
     * 删除数据集
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        Dataset existing = datasetRepository.findById(id);
        Assert.notNull(existing, new SilentException("数据集不存在"));
        existing.delete(datasetRepository);
    }
}
