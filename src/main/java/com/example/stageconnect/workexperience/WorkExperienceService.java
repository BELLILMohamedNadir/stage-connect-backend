package com.example.stageconnect.workexperience;


import java.util.List;

public interface WorkExperienceService {
    WorkExperienceDto create(WorkExperienceDto dto);
    WorkExperienceDto findById(Long id);
    List<WorkExperienceDto> findAll();
    WorkExperienceDto update(Long id, WorkExperienceDto dto);
    void delete(Long id);
}
