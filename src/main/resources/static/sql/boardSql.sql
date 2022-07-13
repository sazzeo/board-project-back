SET
SEARCH_PATH TO BOARD_PROJECT;

------시퀀스 모음----------------------------------------------------------
CREATE SEQUENCE BLOG_SEQ MINVALUE 100001;
CREATE SEQUENCE CATEGORY_SEQ MINVALUE 100001;


---멤버테이블---------------------------------------------------

CREATE TABLE MEMBER
(
    ID          VARCHAR(20) PRIMARY KEY,
    PASSWORD    VARCHAR(50)        NOT NULL,
    NAME        VARCHAR(3)         NOT NULL,
    EMAIL       VARCHAR(30)        NOT NULL,
    PHONE       VARCHAR(13)        NOT NULL,
    POST_CODE   INTEGER NULL,
    ADDR        VARCHAR(30) NULL,
    DETAIL_ADDR VARCHAR(30) NULL,
    REG_DATE    DATE DEFAULT NOW() NOT NULL
);

DROP TABLE MEMBER;

-------------------------------------------------------------
---블로그 테이블---------------------------------------------------
DROP TABLE BLOG;

CREATE TABLE BLOG
(
    BLOG_SEQ     BIGINT     DEFAULT NEXTVAL('BLOG_SEQ') PRIMARY KEY,
    ID           VARCHAR(20) NOT NULL,
    TITLE        VARCHAR(20) NULL,
    SUB_TITLE    VARCHAR(200) NULL,
    URL          VARCHAR(20) NULL,
    TAG_YN       VARCHAR(1) DEFAULT 'Y' NULL,
    TAG_MIN      INTEGER    DEFAULT 3 NULL,
    TAG_CARDINAL INTEGER    DEFAULT 10 NULL,
    REG_DATE     DATE       DEFAULT NOW() NULL
);



-------------------------------------------------------------
---카테고리 테이블---------------------------------------------------


DROP TABLE CATEGORY;

CREATE TABLE CATEGORY
(
    CATEGORY_SEQ BIGINT  DEFAULT NEXTVAL('CATEGORY_SEQ') PRIMARY KEY,
    BLOG_SEQ     BIGINT NOT NULL,
    TITLE        VARCHAR(20) NULL,
    SORT         INTEGER NULL,
    UP_CATEGORY  BIGINT NULL,
    TOTAL_CNT    INTEGER DEFAULT 0 NULL,
    REG_DATE     DATE    DEFAULT NOW() NULL
);


-------------------------------------------------------------
---카테고리 테이블---------------------------------------------------

