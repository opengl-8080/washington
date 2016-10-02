CREATE TABLE proposed_dates (
    id_date DATE NOT NULL,
    round   VARCHAR(50),
    content VARCHAR(1000),
    reserved SMALLINT NOT NULL,
    CONSTRAINT proposed_dates_pk PRIMARY KEY (id_date)
);

CREATE TABLE members (
    login_id VARCHAR(30) NOT NULL,
    name     VARCHAR(30) NOT NULL,
    CONSTRAINT members_pk PRIMARY KEY (login_id)
);

INSERT INTO members VALUES ('member1', 'メンバー１');
INSERT INTO members VALUES ('member2', 'メンバー２');

CREATE TABLE participants (
    proposed_date DATE        NOT NULL,
    time_span     VARCHAR(10) NOT NULL,
    login_id      VARCHAR(30) NOT NULL,
    CONSTRAINT participants_pk PRIMARY KEY (proposed_date, time_span, login_id),
    CONSTRAINT participants_fk1 FOREIGN KEY (proposed_date) REFERENCES proposed_dates (id_date),
    CONSTRAINT participants_fk2 FOREIGN KEY (login_id) REFERENCES members (login_id)
);

CREATE TABLE lock_table (
    name VARCHAR(100) NOT NULL,
    CONSTRAINT lock_table_pk PRIMARY KEY (name)
);

INSERT INTO lock_table VALUES ('BATCH_REGISTER_PROPOSED_DATE');


CREATE TABLE HOGE (
    ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    CODE VARCHAR(100),
    CONSTRAINT HOGE_PK PRIMARY KEY (ID),
    CONSTRAINT HOGE_UK1 UNIQUE (CODE)
);

INSERT INTO HOGE (CODE) VALUES ('AAA');
INSERT INTO HOGE (CODE) VALUES ('BBB');

CREATE TABLE FUGA (
    ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    HOGE_ID BIGINT NOT NULL,
    MY_ENUM VARCHAR(100) NOT NULL,
    STRING_VALUE VARCHAR(100),
    CONSTRAINT FUGA_PK PRIMARY KEY (ID),
    CONSTRAINT FUGA_FK1 FOREIGN KEY (HOGE_ID) REFERENCES HOGE (ID),
    CONSTRAINT FUGA_UK1 UNIQUE (HOGE_ID, MY_ENUM)
);

INSERT INTO FUGA (HOGE_ID, MY_ENUM, STRING_VALUE) VALUES (
    (SELECT ID FROM HOGE WHERE CODE = 'AAA'),
    'FOO', 'a'
);

INSERT INTO FUGA (HOGE_ID, MY_ENUM, STRING_VALUE) VALUES (
    (SELECT ID FROM HOGE WHERE CODE = 'AAA'),
    'BAR', 'b'
);

INSERT INTO FUGA (HOGE_ID, MY_ENUM, STRING_VALUE) VALUES (
    (SELECT ID FROM HOGE WHERE CODE = 'BBB'),
    'FOO', 'c'
);

CREATE TABLE FUGA_LIST (
    ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    FUGA_ID BIGINT NOT NULL,
    STRING_VALUE VARCHAR(100),
    CONSTRAINT FUGA_LIST_PK PRIMARY KEY (ID),
    CONSTRAINT FUGA_LIST_FK1 FOREIGN KEY (FUGA_ID) REFERENCES FUGA (ID)
);

INSERT INTO FUGA_LIST (FUGA_ID, STRING_VALUE) VALUES (
    (SELECT ID FROM FUGA WHERE HOGE_ID=(SELECT ID FROM HOGE WHERE CODE='AAA') AND MY_ENUM='FOO'),
    'aaa'
);

INSERT INTO FUGA_LIST (FUGA_ID, STRING_VALUE) VALUES (
    (SELECT ID FROM FUGA WHERE HOGE_ID=(SELECT ID FROM HOGE WHERE CODE='AAA') AND MY_ENUM='FOO'),
    'bbb'
);

INSERT INTO FUGA_LIST (FUGA_ID, STRING_VALUE) VALUES (
    (SELECT ID FROM FUGA WHERE HOGE_ID=(SELECT ID FROM HOGE WHERE CODE='AAA') AND MY_ENUM='BAR'),
    'ccc'
);

INSERT INTO FUGA_LIST (FUGA_ID, STRING_VALUE) VALUES (
    (SELECT ID FROM FUGA WHERE HOGE_ID=(SELECT ID FROM HOGE WHERE CODE='BBB') AND MY_ENUM='FOO'),
    'ddd'
);

INSERT INTO FUGA_LIST (FUGA_ID, STRING_VALUE) VALUES (
    (SELECT ID FROM FUGA WHERE HOGE_ID=(SELECT ID FROM HOGE WHERE CODE='BBB') AND MY_ENUM='FOO'),
    'eee'
);

INSERT INTO FUGA_LIST (FUGA_ID, STRING_VALUE) VALUES (
    (SELECT ID FROM FUGA WHERE HOGE_ID=(SELECT ID FROM HOGE WHERE CODE='BBB') AND MY_ENUM='FOO'),
    'fff'
);