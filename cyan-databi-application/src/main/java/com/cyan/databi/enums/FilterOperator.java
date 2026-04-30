package com.cyan.databi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 过滤操作符
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum FilterOperator {

    EQ("EQ", "等于", "="),
    NE("NE", "不等于", "!="),
    GT("GT", "大于", ">"),
    GTE("GTE", "大于等于", ">="),
    LT("LT", "小于", "<"),
    LTE("LTE", "小于等于", "<="),
    LIKE("LIKE", "包含", "LIKE"),
    NOT_LIKE("NOT_LIKE", "不包含", "NOT LIKE"),
    IN("IN", "在列表中", "IN"),
    NOT_IN("NOT_IN", "不在列表中", "NOT IN"),
    IS_NULL("IS_NULL", "为空", "IS NULL"),
    IS_NOT_NULL("IS_NOT_NULL", "不为空", "IS NOT NULL"),
    BETWEEN("BETWEEN", "在区间内", "BETWEEN");

    private final String code;
    private final String desc;
    private final String sqlOperator;
}
