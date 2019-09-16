CREATE TABLE c804010_job_info
(
    puk VARCHAR(20) NOT NULL COMMENT '流水ID',
    job_no VARCHAR(20) COMMENT '任务编号',
    job_name VARCHAR(80) COMMENT '任务名称',
    job_group VARCHAR(120) COMMENT '分组名称',
    job_status VARCHAR(4) COMMENT '状态',
    job_type VARCHAR(4) COMMENT '任务类别',
    cron_expression VARCHAR(200) COMMENT 'cron表达式',
    description VARCHAR(400) COMMENT '描述',
    is_concurrent VARCHAR(4) COMMENT '任务是否有状态',
    spring_bean_id VARCHAR(64) COMMENT 'spring bean',
    bean_method_name VARCHAR(32) COMMENT '任务调用的方法名',
    api_mode VARCHAR(4) COMMENT '接口调用模式',
    api_name VARCHAR(80) COMMENT '接口名称',
    api_url VARCHAR(200) COMMENT '接口URL',
    api_param TEXT COMMENT '接口参数',
    meno VARCHAR(200) COMMENT '备注',
    fb1 VARCHAR(40) COMMENT '备用1',
    fb2 VARCHAR(20) COMMENT '备用2',
    fb3 VARCHAR(20) COMMENT '备用3',
    fb4 VARCHAR(200) COMMENT '备用4',
    fb5 VARCHAR(20) COMMENT '备用5',
    eb1 VARCHAR(40) COMMENT '扩展1',
    eb2 VARCHAR(80) COMMENT '扩展2',
    eb3 VARCHAR(20) COMMENT '扩展3',
    eb4 VARCHAR(10) COMMENT '扩展4',
    eb5 VARCHAR(20) COMMENT '扩展5',
    del_flag CHAR(1) DEFAULT '0' NOT NULL COMMENT '有效标识',
    create_time VARCHAR(24) DEFAULT '2017/11/12 23:00:00' NOT NULL COMMENT '创建时间',
    creator VARCHAR(20) DEFAULT 'SYSTEM' NOT NULL COMMENT '创建者',
    update_time VARCHAR(24) DEFAULT '2017/11/12 23:00:00' COMMENT '更新时间',
    updator VARCHAR(20) COMMENT '最后更新者',
PRIMARY KEY (puk)
) COMMENT '定时任务配置表'
;
