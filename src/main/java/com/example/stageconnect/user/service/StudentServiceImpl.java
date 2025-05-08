package com.example.stageconnect.user.service;


import com.example.stageconnect.user.dto.StudentDto;
import com.example.stageconnect.user.mapper.StudentMapper;
import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements UserService <StudentDto>{

    private final UserRepository repository;
    private final StudentMapper mapper;


    @Override
    public StudentDto findById(Long id) {
        Student entity = (Student) repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return mapper.mapFrom(entity);
    }

    @Override
    public List<StudentDto> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> {
                    if (entity instanceof Student) {
                        return mapper.mapFrom((Student) entity);
                    } else {
                        throw new EntityNotFoundException("Unknown user type");
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto update(Long id, StudentDto dto) {
        Student existing = (Student) repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        mapper.updateEntityFromDto(dto, existing);
        Student updated = repository.save(existing);
        return mapper.mapFrom(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Education not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
