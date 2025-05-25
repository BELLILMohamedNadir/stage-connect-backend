CREATE TABLE applied_applications (
  student_id BIGINT NOT NULL,
  application_id BIGINT NOT NULL,
  PRIMARY KEY (student_id, application_id),
  CONSTRAINT fk_saved_offer_user FOREIGN KEY (student_id) REFERENCES student(id),
  CONSTRAINT fk_saved_offer_offer FOREIGN KEY (application_id) REFERENCES Application(id)
);
