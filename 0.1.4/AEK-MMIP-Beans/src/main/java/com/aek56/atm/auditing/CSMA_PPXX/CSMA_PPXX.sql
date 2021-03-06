CREATE TABLE csma_ppxx
(
    puk VARCHAR(24) DEFAULT '0' NOT NULL,
    p01_puk VARCHAR(24) NOT NULL,
    p02_sjlb VARCHAR(20) DEFAULT '2',
    k01_csid VARCHAR(24),
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
    f01_ppqc VARCHAR(80),
    f02_ppqcpym VARCHAR(40),
    f03_ppzl VARCHAR(20),
    f04_ppzmd VARCHAR(20),
    f05_ppsshj VARCHAR(20),
    f06_pply VARCHAR(20),
    f07_ppsmzq VARCHAR(20),
    f08_ppnwx VARCHAR(20),
    f09_ppssxy VARCHAR(20),
    f10_ppfzr VARCHAR(20),
    f11_shfwrx VARCHAR(20),
    f12_ssgj VARCHAR(20),
    f13_logo_url VARCHAR(80),
    f14_csqm VARCHAR(80),
    f15_csqmpym VARCHAR(80),
    f16 VARCHAR(200),
    f17 VARCHAR(80),
    f18 VARCHAR(80),
    f19 VARCHAR(80),
    f20 VARCHAR(80),
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
