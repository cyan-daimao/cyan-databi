package com.cyan.databi.application.analysis.impl;

import com.cyan.arch.common.api.Assert;
import com.cyan.arch.common.api.Response;
import com.cyan.arch.common.api.SilentException;
import com.cyan.databi.application.analysis.AnalysisService;
import com.cyan.databi.application.analysis.bo.ChartDataBO;
import com.cyan.databi.application.analysis.cmd.AnalysisCmd;
import com.cyan.databi.domain.dataset.Dataset;
import com.cyan.databi.domain.dataset.repository.DatasetRepository;
import com.cyan.databi.enums.DatasetSourceType;
import com.cyan.datagateway.client.SqlGatewayClient;
import com.cyan.datagateway.client.cmd.SqlExecuteCmd;
import com.cyan.datagateway.client.dto.SqlExecuteResultDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 分析应用服务实现
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

    private final DatasetRepository datasetRepository;
    private final SqlGatewayClient sqlGatewayClient;
    private final SqlBuilder sqlBuilder;

    public AnalysisServiceImpl(DatasetRepository datasetRepository,
                               SqlGatewayClient sqlGatewayClient,
                               SqlBuilder sqlBuilder) {
        this.datasetRepository = datasetRepository;
        this.sqlGatewayClient = sqlGatewayClient;
        this.sqlBuilder = sqlBuilder;
    }

    /**
     * 执行分析，生成SQL并查询数据
     */
    @Override
    public ChartDataBO execute(AnalysisCmd cmd, String executor) {
        Dataset dataset = datasetRepository.findById(cmd.getDatasetId());
        Assert.notNull(dataset, new SilentException("数据集不存在"));

        String sql = sqlBuilder.build(dataset, cmd);
        long startTime = System.currentTimeMillis();

        SqlExecuteCmd executeCmd = new SqlExecuteCmd().setSql(sql).setPassport(executor);
        Response<SqlExecuteResultDTO> response;
        try {
            response = sqlGatewayClient.executeStarRocksSql(executeCmd);
        } catch (Exception e) {
            return new ChartDataBO()
                    .setStatus("FAILED")
                    .setCostTimeMs(System.currentTimeMillis() - startTime)
                    .setSql(sql)
                    .setErrorMessage("数据网关调用失败: " + e.getMessage());
        }
        long costTime = System.currentTimeMillis() - startTime;

        SqlExecuteResultDTO result = response.getData();
        if (result == null) {
            return new ChartDataBO()
                    .setStatus("FAILED")
                    .setCostTimeMs(costTime)
                    .setSql(sql)
                    .setErrorMessage(response.getMessage());
        }

        List<String> columns = new ArrayList<>();
        List<Map<String, Object>> rows = Optional.ofNullable(result.getData()).orElse(List.of());
        if (!rows.isEmpty()) {
            columns = new ArrayList<>(rows.get(0).keySet());
        }

        return new ChartDataBO()
                .setStatus(result.getStatus())
                .setCostTimeMs(result.getCostTimeMs() != null ? result.getCostTimeMs() : costTime)
                .setColumns(columns)
                .setRows(rows)
                .setSql(sql)
                .setChartType(cmd.getChartType() != null ? cmd.getChartType().name() : null)
                .setErrorMessage(result.getErrorMessage());
    }

    /**
     * 预览SQL（不执行）
     */
    @Override
    public String previewSql(AnalysisCmd cmd) {
        Dataset dataset = datasetRepository.findById(cmd.getDatasetId());
        Assert.notNull(dataset, new SilentException("数据集不存在"));
        return sqlBuilder.build(dataset, cmd);
    }
}
