CREATE TABLE operations_history
(
    id            SERIAL PRIMARY KEY,
    user_id       BIGINT  NOT NULL,
    type          TEXT                         NOT NULL,
    entry         TEXT                        NOT NULL,
    creation_time TIMESTAMP                    NOT NULL
);
