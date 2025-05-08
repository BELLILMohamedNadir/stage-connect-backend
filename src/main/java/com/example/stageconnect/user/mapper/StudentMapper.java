package com.example.stageconnect.user.mapper;

import com.example.stageconnect.mappers.Mapper;
import com.example.stageconnect.user.dto.StudentDto;
import com.example.stageconnect.user.model.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements Mapper<Student, StudentDto> {

    private ModelMapper mapper;

    public StudentMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Student mapTo(StudentDto dto) {
        return mapper.map(dto, Student.class);
    }

    @Override
    public StudentDto mapFrom(Student user) {
        return mapper.map(user, StudentDto.class);
    }

    @Override
    public void updateEntityFromDto(StudentDto dto, Student entity) {
        mapper.map(dto, entity);
    }
}
