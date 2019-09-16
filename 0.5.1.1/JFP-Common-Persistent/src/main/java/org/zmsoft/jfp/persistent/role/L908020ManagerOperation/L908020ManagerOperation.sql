CREATE TABLE l908020_manager_operation
(
    puk VARCHAR(20) NOT NULL COMMENT '流水ID',
    user_id VARCHAR(20) COMMENT '用户ID',
    user_account VARCHAR(40) COMMENT '用户账户',
    name VARCHAR(80) COMMENT '用户姓名',
    edit_type VARCHAR(20) COMMENT '变更类型',
    edit_before VARCHAR(200) COMMENT '变更前内容',
    edit_after VARCHAR(200) COMMENT '变更后内容',
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
    hdp VARCHAR(20) COMMENT '数据归属系统',
    del_flag CHAR(1) DEFAULT '0' COMMENT '有效标识',
    create_time VARCHAR(24) DEFAULT '2017-12-12' NOT NULL COMMENT '创建时间',
    creator VARCHAR(20) DEFAULT 'SYSTEM' NOT NULL COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator VARCHAR(20) COMMENT '最后更新者',
PRIMARY KEY (puk)
) COMMENT '系统管理用户操作日志表'
;
