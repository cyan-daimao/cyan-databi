package com.cyan.databi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 聚合函数类型
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AggregateType {

    SUM("SUM", "求和"),
    AVG("AVG", "平均值"),
    COUNT("COUNT", "计数"),
    MAX("MAX", "最大值"),
    MIN("MIN", "最小值"),
    COUNT_DISTINCT("COUNT_DISTINCT", "去重计数");

    private final String code;
    private final String desc;
}
