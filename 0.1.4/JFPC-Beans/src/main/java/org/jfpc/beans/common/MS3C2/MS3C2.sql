CREATE TABLE ms3c2
(
    puk VARCHAR(24) DEFAULT '0' NOT NULL,
    k01_ywbsid VARCHAR(24),
    k02_sjhm VARCHAR(24),
    k03_dxwgid VARCHAR(24),
    f01_wgzt VARCHAR(20),
    f02_fszt VARCHAR(20) DEFAULT '0',
    f03_dxnr VARCHAR(400),
    f04_wgfhzt VARCHAR(20),
    bbb VARCHAR(200),
    fb1 VARCHAR(80),
    fb2 VARCHAR(80),
    fb3 VARCHAR(20),
    fb4 VARCHAR(20),
    fb5 VARCHAR(20),
    eb1 VARCHAR(80),
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
PRIMARY KEY (puk)
)
