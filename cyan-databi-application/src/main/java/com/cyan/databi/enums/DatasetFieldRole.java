package com.cyan.databi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据集字段角色
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum DatasetFieldRole {

    DIMENSION("DIMENSION", "维度"),
    METRIC("METRIC", "指标");

    private final String code;
    private final String desc;
}
