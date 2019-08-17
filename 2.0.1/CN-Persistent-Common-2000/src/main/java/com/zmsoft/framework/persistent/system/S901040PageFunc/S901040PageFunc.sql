CREATE TABLE s901040_page_func
(
    id VARCHAR(64) NOT NULL COMMENT '流水ID',
    page_id VARCHAR(64) COMMENT '所属画面ID',
    page_name VARCHAR(80) COMMENT '所属画面名称',
    page_ver VARCHAR(20) COMMENT '画面版本号',
    func_name VARCHAR(80) COMMENT '按钮名称',
    func_sort_num BIGINT(10) COMMENT '按钮排列顺序',
    func_url VARCHAR(200) COMMENT '按钮URL',
    meno VARCHAR(200) COMMENT '备注',
    fb1 VARCHAR(40) COMMENT '备用1',
    fb2 VARCHAR(20) COMMENT '备用2',
    fb3 VARCHAR(50) COMMENT '备用3',
    fb4 VARCHAR(200) COMMENT '备用4',
    fb5 VARCHAR(80) COMMENT '备用5',
    eb1 VARCHAR(40) COMMENT '扩展1',
    eb2 VARCHAR(80) COMMENT '扩展2',
    eb3 VARCHAR(20) COMMENT '扩展3',
    eb4 VARCHAR(10) COMMENT '扩展4',
    eb5 VARCHAR(20) COMMENT '扩展5',
    hdp VARCHAR(20) COMMENT '数据归属系统',
    del_flag CHAR(1) DEFAULT '0' COMMENT '有效标识',
    create_time VARCHAR(24) DEFAULT '2018-02-02' NOT NULL COMMENT '创建时间',
    creator VARCHAR(64) DEFAULT 'SYSTEM' NOT NULL COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator VARCHAR(64) COMMENT '最后更新者',
PRIMARY KEY (id)
) COMMENT '页面按钮'
;
