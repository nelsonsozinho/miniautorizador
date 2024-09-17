CREATE TABLE transaction (
    id                  binary(16) UNIQUE NOT NULL,
    id_card             binary(16),
    value               DECIMAL(15,2),
    last_modified_at    timestamp not null
);

ALTER TABLE transaction ADD CONSTRAINT transaction_pkey PRIMARY KEY (id);