package com.cyan.databi.adapter.chart.http.convert;

import com.cyan.arch.common.mapstruct.MapstructConvert;
import com.cyan.databi.adapter.chart.http.dto.ChartDTO;
import com.cyan.databi.application.chart.bo.ChartBO;
import com.cyan.databi.application.chart.cmd.ChartCmd;
import com.cyan.databi.client.ChartSaveRpcCmd;
import com.cyan.databi.enums.ChartType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 图表适配器层转换器
 * <p>
 * 负责 BO → DTO、RPC Cmd → Application Cmd 的转换
 *
 * @author cy.Y
 * @since 1.0.0
 */
@Mapper(uses = MapstructConvert.class)
public interface ChartAdapterConvert {

    ChartAdapterConvert INSTANCE = Mappers.getMapper(ChartAdapterConvert.class);

    /**
     * BO 转 DTO
     */
    ChartDTO toChartDTO(ChartBO chartBO);

    /**
     * RPC 保存图表入参转应用层命令对象
     */
    default ChartCmd toChartCmd(ChartSaveRpcCmd rpcCmd) {
        if (rpcCmd == null) {
            return null;
        }
        ChartCmd cmd = new ChartCmd();
        cmd.setName(rpcCmd.getName());
        cmd.setDescription(rpcCmd.getDescription());
        cmd.setChartType(rpcCmd.getChartType() != null ? ChartType.valueOf(rpcCmd.getChartType()) : null);
        cmd.setMetricAnalysisCmd(rpcCmd.getMetricAnalysisCmd());
        return cmd;
    }
}
