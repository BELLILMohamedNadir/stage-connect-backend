package com.example.stageconnect.user.repository;

import com.example.stageconnect.user.model.BaseUser;
import com.example.stageconnect.user.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}

