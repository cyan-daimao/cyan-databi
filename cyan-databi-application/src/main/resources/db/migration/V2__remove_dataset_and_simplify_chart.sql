-- 删除数据集表（数据集模块已废弃）
DROP TABLE IF EXISTS bi_dataset;

-- 删除图表表中的数据集相关字段
ALTER TABLE bi_chart
    DROP COLUMN dataset_id,
    DROP COLUMN analysis_type,
    DROP INDEX idx_dataset_id,
    DROP INDEX idx_analysis_type;
