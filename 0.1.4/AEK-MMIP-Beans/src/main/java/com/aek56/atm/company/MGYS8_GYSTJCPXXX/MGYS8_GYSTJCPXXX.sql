CREATE TABLE mgys8_gystjcpxxx
(
    puk VARCHAR(24) DEFAULT '0' NOT NULL,
    p11_gysid VARCHAR(24) NOT NULL,
    n01_cpxjc VARCHAR(80),
    n02_cpxjp VARCHAR(40),
    k01_csid VARCHAR(24),
    k02_cpxlb VARCHAR(24),
    k03 VARCHAR(20),
    f01_zwmc VARCHAR(80),
    f02_ywmc VARCHAR(80),
    f03_zwmcpym VARCHAR(80),
    f04_ywmcsx VARCHAR(80),
    f05_zwms VARCHAR(24),
    f06_ywms VARCHAR(24),
    f07_jzkm VARCHAR(24),
    f08_zdhcd VARCHAR(24),
    f09_cpxcd VARCHAR(24),
    f10_mjfs VARCHAR(24),
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
PRIMARY KEY (puk , p11_gysid)
)
