package com.cyan.databi.infra.persistence.dashboard.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 看板数据对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("bi_dashboard")
public class DashboardDO {

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 看板名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 看板描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 布局配置 JSON
     */
    @TableField(value = "layout_config")
    private String layoutConfig;

    /**
     * 图表引用列表 JSON
     */
    @TableField(value = "chart_refs")
    private String chartRefs;

    /**
     * 创建人
     */
    @TableField(value = "created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    @TableField(value = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    @TableField(value = "deleted_at")
    @TableLogic(value = "null", delval = "now()")
    private LocalDateTime deletedAt;
}
