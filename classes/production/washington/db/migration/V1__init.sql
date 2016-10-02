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
