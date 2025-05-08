package com.example.stageconnect.project;


import com.example.stageconnect.workexperience.WorkExperienceDto;

import java.util.List;

public interface ProjectService {
    ProjectDto create(ProjectDto dto);
    ProjectDto findById(Long id);
    List<ProjectDto> findAll();
    ProjectDto update(Long id, ProjectDto dto);
    void delete(Long id);
}
