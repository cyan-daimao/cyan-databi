package com.cyan.databi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 排序方向
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum SortDirection {

    ASC("ASC", "升序"),
    DESC("DESC", "降序");

    private final String code;
    private final String desc;
}
