package com.example.stageconnect.user.service;


import com.example.stageconnect.user.dto.RecruiterDto;
import com.example.stageconnect.user.dto.StudentDto;
import com.example.stageconnect.user.mapper.RecruiterMapper;
import com.example.stageconnect.user.model.Recruiter;
import com.example.stageconnect.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RecruiterServiceImpl implements UserService <RecruiterDto> {

    private final UserRepository repository;
    private final RecruiterMapper mapper;


    @Override
    public RecruiterDto findById(Long id) {
        Recruiter entity = (Recruiter) repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return mapper.mapFrom(entity);
    }

    @Override
    public List<RecruiterDto> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> {
                    if (entity instanceof Recruiter) {
                        return mapper.mapFrom((Recruiter) entity);
                    } else {
                        throw new EntityNotFoundException("Unknown user type");
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public RecruiterDto update(Long id, RecruiterDto dto) {
        Recruiter existing = (Recruiter) repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        mapper.updateEntityFromDto(dto, existing);
        Recruiter updated = repository.save(existing);
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
