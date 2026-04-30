package com.cyan.databi.domain.dataset;

import com.cyan.arch.common.api.Assert;
import com.cyan.arch.common.api.SilentException;
import com.cyan.databi.domain.dataset.repository.DatasetRepository;
import com.cyan.databi.domain.dataset.valobj.DatasetFieldValObj;
import com.cyan.databi.enums.DatasetSourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据集领域对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Dataset {

    /**
     * 主键
     */
    private String id;

    /**
     * 数据集名称
     */
    private String name;

    /**
     * 数据集描述
     */
    private String description;

    /**
     * 数据来源类型：TABLE / SQL
     */
    private DatasetSourceType sourceType;

    /**
     * 来源数据表（sourceType=TABLE时使用）
     */
    private String sourceTable;

    /**
     * 来源SQL（sourceType=SQL时使用）
     */
    private String sourceSql;

    /**
     * 字段列表
     */
    private List<DatasetFieldValObj> fields;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;

    /**
     * 保存数据集
     */
    public Dataset save(DatasetRepository repository) {
        Assert.isBlank(this.id, new SilentException("新增时id必须为空"));
        Assert.notBlank(this.name, new SilentException("数据集名称不能为空"));
        Assert.notNull(this.sourceType, new SilentException("数据来源类型不能为空"));
        if (this.sourceType == DatasetSourceType.TABLE) {
            Assert.notBlank(this.sourceTable, new SilentException("数据表不能为空"));
        } else {
            Assert.notBlank(this.sourceSql, new SilentException("自定义SQL不能为空"));
        }
        return repository.save(this);
    }

    /**
     * 更新数据集
     */
    public Dataset update(DatasetRepository repository) {
        Assert.notBlank(this.id, new SilentException("更新时id不能为空"));
        Assert.notBlank(this.name, new SilentException("数据集名称不能为空"));
        return repository.updateById(this);
    }

    /**
     * 删除数据集
     */
    public void delete(DatasetRepository repository) {
        Assert.notBlank(this.id, new SilentException("删除时id不能为空"));
        repository.deleteById(this.id);
    }
}
