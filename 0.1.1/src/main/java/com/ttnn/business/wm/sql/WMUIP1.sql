CREATE TABLE wmuip1
(
    puk VARCHAR(20) DEFAULT '0' NOT NULL,
    k01 VARCHAR(20),
    f01 VARCHAR(40),
    f02 VARCHAR(20),
    f03 VARCHAR(20),
    f04 VARCHAR(20),
    f05 VARCHAR(4) DEFAULT '0',
    f06 VARCHAR(20) DEFAULT '0',
    bbb VARCHAR(80),
    fb1 VARCHAR(20),
    fb2 VARCHAR(20),
    fb3 VARCHAR(20),
    fb4 VARCHAR(20),
    fb5 VARCHAR(20),
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
