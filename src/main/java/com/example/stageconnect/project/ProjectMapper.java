package com.example.stageconnect.project;

import com.example.stageconnect.mappers.Mapper;
import com.example.stageconnect.workexperience.WorkExperience;
import com.example.stageconnect.workexperience.WorkExperienceDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements Mapper<Project, ProjectDto> {

    private ModelMapper mapper;

    public ProjectMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Project mapTo(ProjectDto projectDto) {
        return mapper.map(projectDto, Project.class);
    }

    @Override
    public ProjectDto mapFrom(Project project) {
        return mapper.map(project, ProjectDto.class);
    }

    @Override
    public void updateEntityFromDto(ProjectDto dto, Project entity) {
        mapper.map(dto, entity);
    }
}
