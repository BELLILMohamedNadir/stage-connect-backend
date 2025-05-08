package com.example.stageconnect.education;

import com.example.stageconnect.internship.Internship;
import com.example.stageconnect.internship.InternshipDto;
import com.example.stageconnect.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EducationMapper implements Mapper<Education, EducationDto> {

    private ModelMapper mapper;

    public EducationMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Education mapTo(EducationDto educationDto) {
        return mapper.map(educationDto, Education.class);
    }

    @Override
    public EducationDto mapFrom(Education education) {
        return mapper.map(education, EducationDto.class);
    }

    @Override
    public void updateEntityFromDto(EducationDto dto, Education entity) {
        mapper.map(dto, entity);
    }
}
