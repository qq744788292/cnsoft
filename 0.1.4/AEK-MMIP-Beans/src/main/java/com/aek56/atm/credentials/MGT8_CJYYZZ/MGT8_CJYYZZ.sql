CREATE TABLE mgt8_cjyyzz
(
    puk VARCHAR(24) DEFAULT '0' NOT NULL,
    p01_yyid VARCHAR(24) NOT NULL,
    k01_gysid VARCHAR(24) NOT NULL,
    k02_sccj_qyid VARCHAR(24) NOT NULL,
    k03_zjbh VARCHAR(80),
    k04_zjlb VARCHAR(20) DEFAULT 'CJ_YYZZ',
    f01_fzdwmc VARCHAR(80),
    f02_fzrq VARCHAR(20),
    f03_yxnx VARCHAR(20),
    f04_yxksrq VARCHAR(20),
    f05_yxzzrq VARCHAR(20),
    f06_shzt VARCHAR(20),
    f07 VARCHAR(20),
    f08 VARCHAR(20),
    f09 VARCHAR(20),
    f10 VARCHAR(20),
    f11 VARCHAR(20),
    f12 VARCHAR(20),
    f13 VARCHAR(20),
    f14 VARCHAR(20),
    f15 VARCHAR(20),
    f16 VARCHAR(20),
    f17 VARCHAR(20),
    f18 VARCHAR(20),
    f19_wlwjbcwz VARCHAR(200),
    f20_sfty VARCHAR(20) DEFAULT '0',
    bbb VARCHAR(200),
    fb1 VARCHAR(40),
    fb2 VARCHAR(80),
    fb3 VARCHAR(20),
    fb4 VARCHAR(20),
    fb5 VARCHAR(20),
    eb1 VARCHAR(40),
    eb2 VARCHAR(80),
    eb3 VARCHAR(20),
    eb4 VARCHAR(20),
    eb5 VARCHAR(24),
    ggg VARCHAR(24) DEFAULT 'SYSTEM',
    ppp VARCHAR(24) DEFAULT 'SYSTEM',
    ddd VARCHAR(4) DEFAULT '0' NOT NULL,
    cc1 VARCHAR(24) DEFAULT '2014/2/8' NOT NULL,
    cc2 VARCHAR(24) DEFAULT 'SYSTEM' NOT NULL,
    uu1 VARCHAR(24),
    uu2 VARCHAR(24),
PRIMARY KEY (puk , p01_yyid , k01_gysid , k02_sccj_qyid)
)
