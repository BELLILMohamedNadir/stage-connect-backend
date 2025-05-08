package com.example.stageconnect.education;

import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository repository;
    private final EducationMapper mapper;
    private final UserRepository userRepository;

    @Override
    public EducationDto create(EducationDto dto) {
        Student user = (Student) userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Education entity = mapper.mapTo(dto);
        entity.setStudent(user);

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
