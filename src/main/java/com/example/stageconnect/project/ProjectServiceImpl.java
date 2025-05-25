package com.example.stageconnect.project;

import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper mapper;
    private final StudentRepository studentRepository;

    @Override
    public ProjectDto create(ProjectDto dto) {
        Student user = (Student) studentRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project entity = mapper.mapTo(dto);
        entity.setStudent(user);

        entity.setId(null);

        Project saved = repository.save(entity);
        return mapper.mapFrom(saved);
    }

    @Override
    public ProjectDto findById(Long id) {
        Project entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
        return mapper.mapFrom(entity);
    }

    @Override
    public List<ProjectDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto update(Long id, ProjectDto dto) {
        Project existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));

        mapper.updateEntityFromDto(dto, existing);
        Project updated = repository.save(existing);
        return mapper.mapFrom(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Project not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
