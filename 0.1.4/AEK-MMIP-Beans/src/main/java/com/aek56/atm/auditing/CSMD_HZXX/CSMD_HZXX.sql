CREATE TABLE csmd_hzxx
(
    puk VARCHAR(24) DEFAULT '0' NOT NULL,
    p01_puk VARCHAR(24) NOT NULL,
    p02_sjlb VARCHAR(20) DEFAULT '2',
    k01_yyid VARCHAR(24),
    k02_sfzhm VARCHAR(24),
    k03 VARCHAR(20),
    n01_shrid VARCHAR(24),
    n02_shrxm VARCHAR(40),
    n03_shzt VARCHAR(20) DEFAULT '2',
    n04_shly VARCHAR(200),
    n05_qymc VARCHAR(80),
    n06_sqrxm VARCHAR(40),
    n07_dxtz VARCHAR(20) DEFAULT '1',
    n08_dxjlid VARCHAR(24),
    n09 VARCHAR(24),
    n10 VARCHAR(40),
    f01_hzxm VARCHAR(80),
    f02_hzxmpym VARCHAR(80),
    f03_csnyr VARCHAR(20),
    f04_xx VARCHAR(20),
    f05 VARCHAR(20) DEFAULT '0',
    f06 VARCHAR(80),
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
    f19 VARCHAR(20),
    f20 VARCHAR(20),
    bbb VARCHAR(80),
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
PRIMARY KEY (puk , p01_puk)
)
