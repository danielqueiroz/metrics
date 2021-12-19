CREATE TABLE machine (
    id   VARCHAR(20)  NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE PARAMETER (
    machine_id VARCHAR(20) NOT NULL,
    name VARCHAR(30)  NOT NULL,
    value NUMBER NOT NULL,
    reported_time TIMESTAMP NOT NULL,
    PRIMARY KEY (machine_id, name, reported_time)
);
