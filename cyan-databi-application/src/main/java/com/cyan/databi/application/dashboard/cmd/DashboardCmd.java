package com.cyan.databi.application.dashboard.cmd;

import com.cyan.databi.domain.dashboard.valobj.ChartRefValObj;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 看板命令对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DashboardCmd {

    /**
     * 看板名称
     */
    @NotBlank(message = "看板名称不能为空")
    private String name;

    /**
     * 看板描述
     */
    private String description;

    /**
     * 布局配置 JSON
     */
    private String layoutConfig;

    /**
     * 图表引用列表
     */
    private List<ChartRefValObj> chartRefs;
}
