ALTER TABLE transaction
    ADD CONSTRAINT fk_transaction_card_id FOREIGN KEY (id_card) REFERENCES card(id);