package com.example.stageconnect.messaging.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    Optional<Room> findByStudentIdAndRecruiterId(Long studentId, Long recruiterId);
    Optional<List<Room>> findByStudentId(Long studentId);
    Optional<List<Room>> findByRecruiterId(Long recruiterId);
}
