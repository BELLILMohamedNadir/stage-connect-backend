package com.example.stageconnect.user.repository;

import com.example.stageconnect.user.model.BaseUser;
import com.example.stageconnect.user.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {
    Optional<Recruiter> findByEmail(String email);
}

