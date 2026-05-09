package com.cyan.databi.adapter.dashboard.http.dto;

import com.cyan.databi.domain.dashboard.valobj.ChartRefValObj;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 看板数据传输对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DashboardDTO {

    /**
     * 主键
     */
    private String id;

    /**
     * 看板名称
     */
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

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedAt;
}
