package com.example.stageconnect.user.repository;

import com.example.stageconnect.user.model.BaseUser;
import com.example.stageconnect.user.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BaseUser, Long> {
    Optional<BaseUser> findByEmail(String email);
}

