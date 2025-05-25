CREATE TABLE saved_offers (
    user_id BIGINT NOT NULL,
    offer_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, offer_id),
    CONSTRAINT fk_saved_offer_user FOREIGN KEY (user_id) REFERENCES base_user(id),
    CONSTRAINT fk_saved_offer_offer FOREIGN KEY (offer_id) REFERENCES offer(id)
);
