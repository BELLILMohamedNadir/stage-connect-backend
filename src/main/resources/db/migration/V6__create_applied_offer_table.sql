DROP TABLE applied_applications;
DROP TABLE applied_offers;
CREATE TABLE applied_offers (
  student_id BIGINT NOT NULL,
  offer_id BIGINT NOT NULL,
  PRIMARY KEY (student_id, offer_id),
  CONSTRAINT fk_applied_offer_user FOREIGN KEY (student_id) REFERENCES student(id),
  CONSTRAINT fk_applied_offer_offer FOREIGN KEY (offer_id) REFERENCES Offer(id)
);
