-- 图表表（含指标分析字段）

CREATE TABLE IF NOT EXISTS bi_chart (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    name VARCHAR(200) NOT NULL COMMENT '图表名称',
    description VARCHAR(500) COMMENT '图表描述',
    dataset_id BIGINT COMMENT '关联数据集ID（DATASET类型时使用）',
    analysis_type VARCHAR(20) DEFAULT 'DATASET' COMMENT '分析类型：DATASET-自建数据集, METRICS-指标系统',
    metric_analysis_cmd JSON COMMENT '指标分析DSL（METRICS类型时使用）',
    chart_type VARCHAR(50) NOT NULL COMMENT '图表类型：TABLE/BAR/LINE/PIE/SCATTER/AREA/NUMBER',
    dimension_config JSON COMMENT '维度配置 JSON',
    metric_config JSON COMMENT '指标配置 JSON',
    filter_config JSON COMMENT '过滤配置 JSON',
    order_config JSON COMMENT '排序配置 JSON',
    limit_value INT COMMENT '限制条数',
    sql_content TEXT COMMENT '生成的SQL内容',
    created_by VARCHAR(100) COMMENT '创建人',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    deleted_at DATETIME DEFAULT NULL COMMENT '删除时间（逻辑删除）',
    INDEX idx_name (name),
    INDEX idx_dataset_id (dataset_id),
    INDEX idx_analysis_type (analysis_type),
    INDEX idx_created_by (created_by)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图表表';
