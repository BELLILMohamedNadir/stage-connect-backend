package com.example.stageconnect.language;

import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository repository;
    private final LanguageMapper mapper;
    private final UserRepository userRepository;

    @Override
    public LanguageDto create(LanguageDto dto) {
        Student user = (Student) userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Language entity = mapper.mapTo(dto);
        entity.setStudent(user);

        entity.setId(null);

        Language saved = repository.save(entity);
        return mapper.mapFrom(saved);
    }

    @Override
    public LanguageDto findById(Long id) {
        Language entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Language not found with id: " + id));
        return mapper.mapFrom(entity);
    }

    @Override
    public List<LanguageDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public LanguageDto update(Long id, LanguageDto dto) {
        Language existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Language not found with id: " + id));

        mapper.updateEntityFromDto(dto, existing);
        Language updated = repository.save(existing);
        return mapper.mapFrom(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Language not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
