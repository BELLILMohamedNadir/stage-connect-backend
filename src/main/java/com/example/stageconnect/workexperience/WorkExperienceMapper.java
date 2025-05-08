package com.example.stageconnect.workexperience;

import com.example.stageconnect.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WorkExperienceMapper implements Mapper<WorkExperience, WorkExperienceDto> {

    private ModelMapper mapper;

    public WorkExperienceMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public WorkExperience mapTo(WorkExperienceDto workExperienceDto) {
        return mapper.map(workExperienceDto, WorkExperience.class);
    }

    @Override
    public WorkExperienceDto mapFrom(WorkExperience workExperience) {
        return mapper.map(workExperience, WorkExperienceDto.class);
    }

    @Override
    public void updateEntityFromDto(WorkExperienceDto dto, WorkExperience entity) {
        mapper.map(dto, entity);
    }
}
