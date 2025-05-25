package com.example.stageconnect.workexperience;

import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkExperienceImpl implements WorkExperienceService {

    private final WorkExperienceRepository repository;
    private final WorkExperienceMapper mapper;
    private final StudentRepository studentRepository;

    @Override
    public WorkExperienceDto create(WorkExperienceDto dto) {
        Student user = (Student) studentRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        WorkExperience entity = mapper.mapTo(dto);
        entity.setStudent(user);

        entity.setId(null);

        WorkExperience saved = repository.save(entity);
        return mapper.mapFrom(saved);
    }

    @Override
    public WorkExperienceDto findById(Long id) {
        WorkExperience entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Work experience not found with id: " + id));
        return mapper.mapFrom(entity);
    }

    @Override
    public List<WorkExperienceDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public WorkExperienceDto update(Long id, WorkExperienceDto dto) {
        WorkExperience existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Work experience not found with id: " + id));

        mapper.updateEntityFromDto(dto, existing);
        WorkExperience updated = repository.save(existing);
        return mapper.mapFrom(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Work experience not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
