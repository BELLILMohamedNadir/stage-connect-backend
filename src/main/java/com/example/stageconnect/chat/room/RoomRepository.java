package com.example.stageconnect.chat.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByStudentIdAndRecruiterId(Long studentId, Long recruiterId);
}
