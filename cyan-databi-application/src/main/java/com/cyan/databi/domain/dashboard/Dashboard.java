package com.cyan.databi.domain.dashboard;

import com.cyan.arch.common.api.Assert;
import com.cyan.arch.common.api.SilentException;
import com.cyan.databi.domain.dashboard.repository.DashboardRepository;
import com.cyan.databi.domain.dashboard.valobj.ChartRefValObj;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 看板领域对象
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Dashboard {

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
     * 修改人
     */
    private String updatedBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;

    /**
     * 保存看板
     */
    public Dashboard save(DashboardRepository repository) {
        Assert.isBlank(this.id, new SilentException("新增时id必须为空"));
        Assert.notBlank(this.name, new SilentException("看板名称不能为空"));
        return repository.save(this);
    }

    /**
     * 更新看板
     */
    public Dashboard update(DashboardRepository repository) {
        Assert.notBlank(this.id, new SilentException("更新时id不能为空"));
        Assert.notBlank(this.name, new SilentException("看板名称不能为空"));
        return repository.updateById(this);
    }

    /**
     * 删除看板
     */
    public void delete(DashboardRepository repository) {
        Assert.notBlank(this.id, new SilentException("删除时id不能为空"));
        repository.deleteById(this.id);
    }
}
