package com.cyan.databi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 图表类型
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum ChartType {

    TABLE("TABLE", "表格"),
    BAR("BAR", "柱状图"),
    LINE("LINE", "折线图"),
    PIE("PIE", "饼图"),
    SCATTER("SCATTER", "散点图"),
    AREA("AREA", "面积图"),
    NUMBER("NUMBER", "指标卡"),
    FILTER_SELECT("FILTER_SELECT", "单选下拉框"),
    FILTER_MULTI("FILTER_MULTI", "多选下拉框"),
    FILTER_DATE("FILTER_DATE", "日期选择器"),
    FILTER_DATE_RANGE("FILTER_DATE_RANGE", "日期范围选择器");
    // TASK: done

    private final String code;
    private final String desc;
}
