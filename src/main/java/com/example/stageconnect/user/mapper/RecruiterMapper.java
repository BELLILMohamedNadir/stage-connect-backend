package com.example.stageconnect.user.mapper;

import com.example.stageconnect.mappers.Mapper;
import com.example.stageconnect.user.dto.RecruiterDto;
import com.example.stageconnect.user.dto.StudentDto;
import com.example.stageconnect.user.model.Recruiter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RecruiterMapper implements Mapper<Recruiter, RecruiterDto> {

    private ModelMapper mapper;

    public RecruiterMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Recruiter mapTo(RecruiterDto dto) {
        return mapper.map(dto, Recruiter.class);
    }

    @Override
    public RecruiterDto mapFrom(Recruiter user) {
        return mapper.map(user, RecruiterDto.class);
    }

    @Override
    public void updateEntityFromDto(RecruiterDto dto, Recruiter entity) {
        mapper.map(dto, entity);
    }
}
