package com.example.stageconnect.file;

import com.example.stageconnect.user.model.BaseUser;
import com.example.stageconnect.user.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findAllByUserAndPathEndingWith(BaseUser user, String path);
    Optional<File> findByUserIdAndCurrentAndPathEndingWith(Long userId, Boolean current, String suffix);
}

