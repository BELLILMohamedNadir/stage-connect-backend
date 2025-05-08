package com.example.stageconnect.internship;

import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository repository;
    private final InternshipMapper mapper;
    private final UserRepository userRepository;

    @Override
    public InternshipDto create(InternshipDto dto) {
        Student user = (Student) userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Internship entity = mapper.mapTo(dto);
        entity.setStudent(user);

        entity.setId(null);

        Internship saved = repository.save(entity);
        return mapper.mapFrom(saved);
    }

    @Override
    public InternshipDto findById(Long id) {
        Internship entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Internship not found with id: " + id));
        return mapper.mapFrom(entity);
    }

    @Override
    public List<InternshipDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public InternshipDto update(Long id, InternshipDto dto) {
        Internship existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Internship not found with id: " + id));

        mapper.updateEntityFromDto(dto, existing);
        Internship updated = repository.save(existing);
        return mapper.mapFrom(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Internship not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
