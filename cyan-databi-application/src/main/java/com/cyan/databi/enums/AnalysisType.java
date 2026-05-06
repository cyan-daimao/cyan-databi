package com.cyan.databi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 分析类型
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AnalysisType {

    DATASET("DATASET", "基于自建数据集"),
    METRICS("METRICS", "基于指标系统");

    private final String code;
    private final String desc;
}
