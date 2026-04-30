package com.cyan.databi.application.analysis;

import com.cyan.databi.application.analysis.bo.ChartDataBO;
import com.cyan.databi.application.analysis.cmd.AnalysisCmd;

/**
 * 分析应用服务接口
 *
 * @author cy.Y
 * @since 1.0.0
 */
public interface AnalysisService {

    /**
     * 执行分析，生成SQL并查询数据
     *
     * @param cmd 分析配置命令
     * @param executor 执行人
     * @return 图表数据
     */
    ChartDataBO execute(AnalysisCmd cmd, String executor);

    /**
     * 预览SQL（不执行）
     *
     * @param cmd 分析配置命令
     * @return 生成的SQL
     */
    String previewSql(AnalysisCmd cmd);
}
