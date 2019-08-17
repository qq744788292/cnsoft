CREATE TABLE s901050_classify
(
    id VARCHAR(64) NOT NULL COMMENT '分类ID',
    classify_num BIGINT(10) NOT NULL COMMENT '分类编号',
    classify_name VARCHAR(40) NOT NULL COMMENT '分类名称',
    classify_pic_url VARCHAR(400) NOT NULL COMMENT '分类图标URL',
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
) COMMENT '业务分类'
;
