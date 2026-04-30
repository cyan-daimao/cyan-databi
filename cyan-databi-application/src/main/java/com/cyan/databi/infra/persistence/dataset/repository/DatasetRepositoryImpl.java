package com.cyan.databi.infra.persistence.dataset.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyan.arch.common.api.Page;
import com.cyan.arch.common.util.StrUtils;
import com.cyan.databi.domain.dataset.Dataset;
import com.cyan.databi.domain.dataset.query.DatasetListQuery;
import com.cyan.databi.domain.dataset.query.DatasetPageQuery;
import com.cyan.databi.domain.dataset.repository.DatasetRepository;
import com.cyan.databi.infra.persistence.dataset.convert.DatasetInfraConvert;
import com.cyan.databi.infra.persistence.dataset.dos.DatasetDO;
import com.cyan.databi.infra.persistence.dataset.mappers.DatasetMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 数据集仓储实现
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Repository
public class DatasetRepositoryImpl implements DatasetRepository {

    private final DatasetMapper datasetMapper;

    public DatasetRepositoryImpl(DatasetMapper datasetMapper) {
        this.datasetMapper = datasetMapper;
    }

    /**
     * 分页查询数据集
     */
    @Override
    public Page<Dataset> page(DatasetPageQuery query) {
        LambdaQueryWrapper<DatasetDO> wrapper = new LambdaQueryWrapper<DatasetDO>()
                .like(StrUtils.isNotBlank(query.getName()), DatasetDO::getName, query.getName())
                .eq(StrUtils.isNotBlank(query.getCreatedBy()), DatasetDO::getCreatedBy, query.getCreatedBy())
                .orderByDesc(DatasetDO::getCreatedAt);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DatasetDO> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(query.getCurrent(), query.getSize());
        page = datasetMapper.selectPage(page, wrapper);
        List<Dataset> data = Optional.ofNullable(page.getRecords()).orElse(List.of())
                .stream().map(DatasetInfraConvert.INSTANCE::toDataset).toList();
        return new Page<>(data, page.getCurrent(), page.getSize(), page.getTotal());
    }

    /**
     * 列表查询数据集
     */
    @Override
    public List<Dataset> list(DatasetListQuery query) {
        LambdaQueryWrapper<DatasetDO> wrapper = new LambdaQueryWrapper<DatasetDO>()
                .like(StrUtils.isNotBlank(query.getName()), DatasetDO::getName, query.getName())
                .eq(StrUtils.isNotBlank(query.getCreatedBy()), DatasetDO::getCreatedBy, query.getCreatedBy())
                .orderByDesc(DatasetDO::getCreatedAt);
        List<DatasetDO> dos = datasetMapper.selectList(wrapper);
        return Optional.ofNullable(dos).orElse(List.of())
                .stream().map(DatasetInfraConvert.INSTANCE::toDataset).toList();
    }

    /**
     * 根据ID查询数据集
     */
    @Override
    public Dataset findById(String id) {
        DatasetDO datasetDO = datasetMapper.selectById(id);
        if (datasetDO == null) {
            return null;
        }
        return DatasetInfraConvert.INSTANCE.toDataset(datasetDO);
    }

    /**
     * 保存数据集
     */
    @Override
    public Dataset save(Dataset dataset) {
        DatasetDO datasetDO = DatasetInfraConvert.INSTANCE.toDatasetDO(dataset);
        datasetMapper.insert(datasetDO);
        return findById(datasetDO.getId() + "");
    }

    /**
     * 更新数据集
     */
    @Override
    public Dataset updateById(Dataset dataset) {
        DatasetDO datasetDO = DatasetInfraConvert.INSTANCE.toDatasetDO(dataset);
        datasetMapper.updateById(datasetDO);
        return findById(dataset.getId());
    }

    /**
     * 删除数据集
     */
    @Override
    public void deleteById(String id) {
        datasetMapper.deleteById(id);
    }
}
