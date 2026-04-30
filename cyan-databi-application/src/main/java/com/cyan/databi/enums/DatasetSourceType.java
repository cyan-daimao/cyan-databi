package com.cyan.databi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据集来源类型
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum DatasetSourceType {

    TABLE("TABLE", "数据表"),
    SQL("SQL", "自定义SQL");

    private final String code;
    private final String desc;
}
