DROP TABLE saved_offers;
CREATE TABLE saved_offers (
                              student_id BIGINT NOT NULL,
                              offer_id BIGINT NOT NULL,
                              PRIMARY KEY (student_id, offer_id),
                              CONSTRAINT fk_saved_offer_user FOREIGN KEY (student_id) REFERENCES student(id),
                              CONSTRAINT fk_saved_offer_offer FOREIGN KEY (offer_id) REFERENCES offer(id)
);
