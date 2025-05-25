package com.example.stageconnect.application;

import com.example.stageconnect.certification.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query(value = "SELECT * FROM APPLICATION WHERE STUDENT_ID = :id", nativeQuery = true)
    List<Application> findByStudentId(@Param("id") Long id);

    @Query(value = """
    SELECT a.* FROM application a
    JOIN offer o ON a.offer_id = o.id
    WHERE o.recruiter_id = :id
    """, nativeQuery = true)
    List<Application> findByRecruiterId(@Param("id") Long id);

    @Query(value = "SELECT * FROM APPLICATION WHERE ESTABLISHMENT_ID = :id", nativeQuery = true)
    List<Application> findByEstablishmentId(@Param("id") Long id);
}
