package com.example.stageconnect.education;

import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class EducationServiceImpl implements EducationService {

    private final EducationRepository repository;
    private final EducationMapper mapper;
    private final StudentRepository studentRepository;

    @Override
    public EducationDto create(EducationDto dto) {
        Student user = studentRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Education entity = mapper.mapTo(dto);
        entity.setStudent(user);

        log.info("Saving education: {}", entity);

        entity.setId(null);

        Education saved = repository.save(entity);
        return mapper.mapFrom(saved);
    }

    @Override
    public EducationDto findById(Long id) {
        Education entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Education not found with id: " + id));
        return mapper.mapFrom(entity);
    }

    @Override
    public List<EducationDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public EducationDto update(Long id, EducationDto dto) {
        Education existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Education not found with id: " + id));

        mapper.updateEntityFromDto(dto, existing);
        Education updated = repository.save(existing);
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
