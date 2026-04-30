package com.cyan.databi.application.dataset.cmd;

import com.cyan.databi.domain.dataset.valobj.DatasetFieldValObj;
import com.cyan.databi.enums.DatasetSourceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 数据集命令对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DatasetCmd {

    /**
     * 数据集名称
     */
    @NotBlank(message = "数据集名称不能为空")
    private String name;

    /**
     * 数据集描述
     */
    private String description;

    /**
     * 数据来源类型
     */
    @NotNull(message = "数据来源类型不能为空")
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
}
