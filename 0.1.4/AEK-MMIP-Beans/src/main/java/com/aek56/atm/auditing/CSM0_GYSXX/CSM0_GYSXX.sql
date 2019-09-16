CREATE TABLE csm0_gysxx
(
    puk VARCHAR(24) DEFAULT '0' NOT NULL,
    p01_puk VARCHAR(24) NOT NULL,
    p02_sjlb VARCHAR(20) DEFAULT '2',
    k01_zjjgdm VARCHAR(40),
    k02 VARCHAR(40),
    k03 VARCHAR(80),
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
    f01_qyqc VARCHAR(200),
    f02_qcpym VARCHAR(80),
    f03_gwdz VARCHAR(200),
    f04_clrq VARCHAR(20),
    f05_zczbwy VARCHAR(20),
    f06_frxm VARCHAR(24),
    f07_frsfzid VARCHAR(24),
    f08_fkfs VARCHAR(20),
    f09_khyx VARCHAR(200),
    f10_khyxzh VARCHAR(80),
    f11_szs VARCHAR(40),
    f12_szds VARCHAR(40),
    f13_szqx VARCHAR(40),
    f14_szxxdz VARCHAR(200),
    f15_bgxxdz VARCHAR(200),
    f16_lxdh VARCHAR(80),
    f17_czhm VARCHAR(80),
    f18_pzbm VARCHAR(40),
    f19_logo_url VARCHAR(200),
    f20_qyjyxkzbh VARCHAR(200),
    f21_qyjyxkzksrq VARCHAR(24),
    f22_qyjyxkzzlrq VARCHAR(24),
    f23_qygsswdjzksrq VARCHAR(24),
    f24_qygsswdjzzlrq VARCHAR(24),
    f25_qyyyzzksrq VARCHAR(24),
    f26_qyyyzzzlrq VARCHAR(24),
    f27_qyszgj VARCHAR(80),
    f28_qygsswdjzbh VARCHAR(80),
    f29_qyyyzzbh VARCHAR(80),
    f30_lxrxm VARCHAR(40),
    f31_lxraqyx VARCHAR(80),
    f32_lxrdh VARCHAR(40),
    f33_qyjyxkz VARCHAR(200),
    f34_qygsswdjz VARCHAR(200),
    f35_qyyyzz VARCHAR(200),
    f36_zdlbs VARCHAR(80) DEFAULT '2',
    f37 VARCHAR(80),
    f38 VARCHAR(80),
    f39 VARCHAR(80),
    f40 VARCHAR(80),
    bbb VARCHAR(200),
    fb1 VARCHAR(40),
    fb2 VARCHAR(80),
    fb3 VARCHAR(20),
    fb4 VARCHAR(20),
    fb5 VARCHAR(20),
    eb1 VARCHAR(40),
    eb2 VARCHAR(80),
    eb3 VARCHAR(24),
    eb4 VARCHAR(24),
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
