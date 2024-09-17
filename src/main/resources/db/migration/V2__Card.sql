CREATE TABLE card (
    id              binary(16) UNIQUE NOT NULL,
    password        VARCHAR(100),
    number          VARCHAR(16),
    balance         DECIMAL(15,2),
    last_modified_at timestamp not null
);

ALTER TABLE card ADD CONSTRAINT card_pkey PRIMARY KEY (id);