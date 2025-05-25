package com.example.stageconnect.user.service;


import com.example.stageconnect.file.File;
import com.example.stageconnect.file.FileService;
import com.example.stageconnect.user.dto.StudentDto;
import com.example.stageconnect.user.mapper.StudentMapper;
import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Log4j2
public class StudentServiceImpl implements UserService <StudentDto>{

    private final StudentRepository repository;
    private final StudentMapper mapper;
    private final FileService fileService;


    @Override
    public StudentDto findById(Long id) {
        Student entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return mapper.mapFrom(entity);
    }

    @Override
    public List<StudentDto> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> {
                    if (entity != null) {
                        return mapper.mapFrom((Student) entity);
                    } else {
                        throw new EntityNotFoundException("Unknown user type");
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto update(Long id, StudentDto dto) {
        return null;
    }

    public StudentDto update(StudentDto dto, MultipartFile file) throws IOException {
        Student existing = repository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + dto.getId()));

        mapper.updateEntityFromDto(dto, existing);
        String photoName = null;
        if (file != null) {
            photoName = fileService.saveFile(file, dto.getId());
            existing.setPhoto(photoName);
        }
        Student updated = repository.save(existing);
        StudentDto updatedDto = mapper.mapFrom(updated);
        if (photoName != null) {updatedDto.setPhoto(fileService.generateFileUrl(photoName));}
        return updatedDto;
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Student not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public List<String> addSkills(Long studentId, List<String> skills) {
        Student student  = repository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        student.getSkills().addAll(skills);
        repository.save(student);
        return student.getSkills();
    }
}
