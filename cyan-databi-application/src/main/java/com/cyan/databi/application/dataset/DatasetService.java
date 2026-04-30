package com.cyan.databi.application.dataset;

import com.cyan.arch.common.api.Page;
import com.cyan.databi.application.dataset.bo.DatasetBO;
import com.cyan.databi.application.dataset.cmd.DatasetCmd;
import com.cyan.databi.domain.dataset.query.DatasetListQuery;
import com.cyan.databi.domain.dataset.query.DatasetPageQuery;

import java.util.List;

/**
 * 数据集应用服务接口
 *
 * @author cy.Y
 * @since 1.0.0
 */
public interface DatasetService {

    /**
     * 分页查询数据集
     */
    Page<DatasetBO> page(DatasetPageQuery query);

    /**
     * 列表查询数据集
     */
    List<DatasetBO> list(DatasetListQuery query);

    /**
     * 根据ID查询数据集
     */
    DatasetBO findById(String id);

    /**
     * 保存数据集
     */
    DatasetBO save(DatasetCmd cmd, String createdBy);

    /**
     * 更新数据集
     */
    DatasetBO update(String id, DatasetCmd cmd);

    /**
     * 删除数据集
     */
    void delete(String id);
}
