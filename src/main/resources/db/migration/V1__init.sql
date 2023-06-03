CREATE TABLE employees
(
    id            SERIAL PRIMARY KEY,
    name          TEXT      NOT NULL,
    salary        INTEGER   NOT NULL DEFAULT 0,
    department    TEXT      NOT NULL,
    creation_time TIMESTAMP NOT NULL
);

CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    username          TEXT      NOT NULL,
    password    TEXT      NOT NULL,
    creation_time TIMESTAMP NOT NULL
);

INSERT INTO users(username, password, creation_time)
VALUES ('admin@axa.com', '123', now());
