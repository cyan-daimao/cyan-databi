package com.cyan.databi.domain.dataset.repository;

import com.cyan.arch.common.api.Page;
import com.cyan.databi.domain.dataset.Dataset;
import com.cyan.databi.domain.dataset.query.DatasetListQuery;
import com.cyan.databi.domain.dataset.query.DatasetPageQuery;

import java.util.List;

/**
 * 数据集仓储接口
 *
 * @author cy.Y
 * @since 1.0.0
 */
public interface DatasetRepository {

    /**
     * 分页查询数据集
     */
    Page<Dataset> page(DatasetPageQuery query);

    /**
     * 列表查询数据集
     */
    List<Dataset> list(DatasetListQuery query);

    /**
     * 根据ID查询数据集
     */
    Dataset findById(String id);

    /**
     * 保存数据集
     */
    Dataset save(Dataset dataset);

    /**
     * 更新数据集
     */
    Dataset updateById(Dataset dataset);

    /**
     * 删除数据集
     */
    void deleteById(String id);
}
