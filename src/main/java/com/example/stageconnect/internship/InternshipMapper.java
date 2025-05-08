package com.example.stageconnect.internship;

import com.example.stageconnect.mappers.Mapper;
import com.example.stageconnect.workexperience.WorkExperience;
import com.example.stageconnect.workexperience.WorkExperienceDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InternshipMapper implements Mapper<Internship, InternshipDto> {

    private ModelMapper mapper;

    public InternshipMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Internship mapTo(InternshipDto internshipDto) {
        return mapper.map(internshipDto, Internship.class);
    }

    @Override
    public InternshipDto mapFrom(Internship internship) {
        return mapper.map(internship, InternshipDto.class);
    }

    @Override
    public void updateEntityFromDto(InternshipDto dto, Internship entity) {
        mapper.map(dto, entity);
    }
}
