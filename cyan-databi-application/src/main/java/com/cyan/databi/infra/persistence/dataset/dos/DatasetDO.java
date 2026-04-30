package com.cyan.databi.infra.persistence.dataset.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cyan.databi.enums.DatasetSourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 数据集数据对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("bi_dataset")
public class DatasetDO {

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 数据集名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 数据集描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 数据来源类型
     */
    @TableField(value = "source_type")
    private DatasetSourceType sourceType;

    /**
     * 来源数据表
     */
    @TableField(value = "source_table")
    private String sourceTable;

    /**
     * 来源SQL
     */
    @TableField(value = "source_sql")
    private String sourceSql;

    /**
     * 字段列表 JSON
     */
    @TableField(value = "fields")
    private String fields;

    /**
     * 创建人
     */
    @TableField(value = "created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    @TableField(value = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    @TableField(value = "deleted_at")
    @TableLogic(value = "null", delval = "now()")
    private LocalDateTime deletedAt;
}
