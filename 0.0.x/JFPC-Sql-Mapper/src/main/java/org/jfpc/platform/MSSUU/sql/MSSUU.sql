CREATE TABLE mssuu
(
    puk VARCHAR(24) DEFAULT '0' NOT NULL,
    bbb VARCHAR(80),
    fb1 VARCHAR(64),
    fb2 VARCHAR(24),
    fb3 VARCHAR(24),
    fb4 VARCHAR(20),
    fb5 VARCHAR(20),
    eb1 VARCHAR(20),
    eb2 VARCHAR(20),
    eb3 VARCHAR(20) DEFAULT '1',
    eb4 VARCHAR(20) DEFAULT '1',
    eb5 VARCHAR(20),
    ggg VARCHAR(24) DEFAULT 'SYSTEM',
    ddd VARCHAR(4) DEFAULT '0' NOT NULL,
    cc1 VARCHAR(24) DEFAULT '2014/2/8' NOT NULL,
    cc2 VARCHAR(20) DEFAULT 'SYSTEM' NOT NULL,
    uu1 VARCHAR(24),
    uu2 VARCHAR(20),
PRIMARY KEY (puk)
)
