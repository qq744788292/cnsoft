CREATE TABLE cssp11
(
    puk VARCHAR(20) DEFAULT '0' NOT NULL,
    k01 VARCHAR(20) DEFAULT '0' NOT NULL,
    bbb VARCHAR(80),
    fb1 VARCHAR(4) DEFAULT 'N',
    fb2 VARCHAR(4) DEFAULT 'N',
    fb3 VARCHAR(4) DEFAULT 'N',
    fb4 VARCHAR(4) DEFAULT 'N',
    fb5 VARCHAR(4) DEFAULT 'N',
    eb1 VARCHAR(20),
    eb2 VARCHAR(20),
    eb3 VARCHAR(20),
    eb4 VARCHAR(20),
    eb5 VARCHAR(20),
    ddd CHAR(1) DEFAULT '0' NOT NULL,
    cc1 VARCHAR(24) DEFAULT '2013/5/1 17:14:13' NOT NULL,
    cc2 VARCHAR(20) DEFAULT 'Spook' NOT NULL,
    uu1 VARCHAR(24),
    uu2 VARCHAR(20),
PRIMARY KEY (puk , k01)
)
