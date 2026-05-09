package com.cyan.databi.adapter.dataset.http.dto;

import com.cyan.databi.domain.dataset.valobj.DatasetFieldValObj;
import com.cyan.databi.enums.DatasetSourceType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据集数据传输对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DatasetDTO {

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
     * 数据来源类型
     */
    private DatasetSourceType sourceType;

    /**
     * 来源数据表
     */
    private String sourceTable;

    /**
     * 来源SQL
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedAt;
}
