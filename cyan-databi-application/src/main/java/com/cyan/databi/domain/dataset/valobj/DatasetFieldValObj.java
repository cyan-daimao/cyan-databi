package com.cyan.databi.domain.dataset.valobj;

import com.cyan.databi.enums.DatasetFieldRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 数据集字段值对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DatasetFieldValObj {

    /**
     * 字段名
     */
    private String name;

    /**
     * 字段中文名
     */
    private String comment;

    /**
     * 字段类型
     */
    private String dataType;

    /**
     * 字段角色：维度 / 指标
     */
    private DatasetFieldRole role;
}
