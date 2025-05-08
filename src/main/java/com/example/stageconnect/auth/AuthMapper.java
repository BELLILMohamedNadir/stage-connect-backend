package com.example.stageconnect.auth;

import com.example.stageconnect.mappers.Mapper;
import com.example.stageconnect.user.model.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper implements Mapper<Student, AuthDto> {

    private ModelMapper mapper;

    public AuthMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Student mapTo(AuthDto authDto) {
        return mapper.map(authDto, Student.class);
    }

    @Override
    public AuthDto mapFrom(Student user) {
        return mapper.map(user, AuthDto.class);
    }

    @Override
    public void updateEntityFromDto(AuthDto dto, Student entity) {
        mapper.map(dto, entity);
    }
}
