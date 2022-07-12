SET
SEARCH_PATH TO BOARD_PROJECT;


---멤버테이블---------------------------------------------------

CREATE TABLE MEMBER
(
    ID          VARCHAR(20),
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
    BLOG_SEQ     BIGINT      NOT NULL,
    ID           VARCHAR(20) NOT NULL,
    TITLE        VARCHAR(20) NULL,
    SUB_TITLE    VARCHAR(200) NULL,
    URL          VARCHAR(20) NULL,
    TAG_YN       VARCHAR(1) DEFAULT 'Y' NULL,
    TAG_MIN      INTEGER    DEFAULT 3 NULL,
    TAG_CARDINAL INTEGER    DEFAULT 10 NULL,
    REG_DATE     DATE       DEFAULT NOW() NULL
);

ALTER TABLE BLOG
    ADD CONSTRAINT PK_BLOG PRIMARY KEY (
                                        BLOG_SEQ
        );

-------------------------------------------------------------
---카테고리 테이블---------------------------------------------------


DROP TABLE CATEGORY;

CREATE TABLE CATEGORY
(
    CATEGORY_SEQ BIGINT NOT NULL,
    BLOG_SEQ     BIGINT NOT NULL,
    TITLE        VARCHAR(20) NULL,
    SORT         INTEGER NULL,
    UP_CATEGORY  BIGINT NULL,
    TOTAL_CNT    INTEGER DEFAULT 0 NULL,
    REG_DATE     DATE    DEFAULT NOW() NULL
);

ALTER TABLE CATEGORY
    ADD CONSTRAINT PK_CATEGORY PRIMARY KEY (
                                            CATEGORY_SEQ
        );


-------------------------------------------------------------
---카테고리 테이블---------------------------------------------------

