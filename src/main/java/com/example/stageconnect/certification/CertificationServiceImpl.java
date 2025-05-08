package com.example.stageconnect.certification;

import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {

    private final CertificationRepository repository;
    private final CertificationMapper mapper;
    private final UserRepository userRepository;

    @Override
    public CertificationDto create(CertificationDto dto) {
        Student user = (Student) userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Certification entity = mapper.mapTo(dto);
        entity.setStudent(user);

        entity.setId(null);

        Certification saved = repository.save(entity);
        return mapper.mapFrom(saved);
    }

    @Override
    public CertificationDto findById(Long id) {
        Certification entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certification not found with id: " + id));
        return mapper.mapFrom(entity);
    }

    @Override
    public List<CertificationDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public CertificationDto update(Long id, CertificationDto dto) {
        Certification existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certification not found with id: " + id));

        mapper.updateEntityFromDto(dto, existing);
        Certification updated = repository.save(existing);
        return mapper.mapFrom(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Certification not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
