package com.cyan.databi.domain.chart.valobj;

import com.cyan.databi.enums.FilterOperator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 过滤配置值对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class FilterConfigValObj {

    /**
     * 字段名
     */
    private String field;

    /**
     * 操作符
     */
    private FilterOperator operator;

    /**
     * 过滤值（单个值或列表值）
     */
    private List<String> values;
}
