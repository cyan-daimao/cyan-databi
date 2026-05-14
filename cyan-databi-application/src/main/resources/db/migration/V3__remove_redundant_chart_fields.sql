-- 删除 Chart 冗余字段（已统一迁移到 metric_analysis_cmd JSON 中）
ALTER TABLE bi_chart
    DROP COLUMN IF EXISTS dimension_config,
    DROP COLUMN IF EXISTS metric_config,
    DROP COLUMN IF EXISTS filter_config,
    DROP COLUMN IF EXISTS order_config,
    DROP COLUMN IF EXISTS limit_value,
    DROP COLUMN IF EXISTS sql_content;
