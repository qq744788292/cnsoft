CREATE TABLE wmbm05
(
    puk VARCHAR(20) DEFAULT '0' NOT NULL,
    k01 VARCHAR(20),
    k02 VARCHAR(20),
    k03 VARCHAR(40),
    f01 VARCHAR(40),
    f02 VARCHAR(40),
    f03 VARCHAR(20),
    f04 VARCHAR(40),
    f05 VARCHAR(4),
    f06 VARCHAR(4) DEFAULT '0',
    f07 VARCHAR(20) DEFAULT '0',
    f08 VARCHAR(40),
    f09 VARCHAR(20),
    f10 VARCHAR(20),
    f11 VARCHAR(20),
    f12 VARCHAR(20),
    f13 VARCHAR(20),
    f14 VARCHAR(20) DEFAULT '0',
    f15 VARCHAR(20) DEFAULT '0',
    f16 VARCHAR(20) DEFAULT '0',
    f17 VARCHAR(200),
    f18 VARCHAR(20) DEFAULT '0',
    bbb VARCHAR(80),
    fb1 VARCHAR(20),
    fb2 VARCHAR(20) DEFAULT '0',
    fb3 VARCHAR(20),
    fb4 VARCHAR(20),
    fb5 VARCHAR(20) DEFAULT '1',
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
PRIMARY KEY (puk)
)
