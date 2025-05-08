package com.example.stageconnect.user.service;


import com.example.stageconnect.user.dto.EstablishmentDto;
import com.example.stageconnect.user.dto.StudentDto;
import com.example.stageconnect.user.mapper.EstablishmentMapper;
import com.example.stageconnect.user.model.Establishment;
import com.example.stageconnect.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EstablishmentServiceImpl implements UserService <EstablishmentDto> {

    private final UserRepository repository;
    private final EstablishmentMapper mapper;


    @Override
    public EstablishmentDto findById(Long id) {
        Establishment entity = (Establishment) repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return mapper.mapFrom(entity);
    }

    @Override
    public List<EstablishmentDto> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> {
                    if (entity instanceof Establishment) {
                        return mapper.mapFrom((Establishment) entity);
                    } else {
                        throw new EntityNotFoundException("Unknown user type");
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public EstablishmentDto update(Long id, EstablishmentDto dto) {
        Establishment existing = (Establishment) repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        mapper.updateEntityFromDto(dto, existing);
        Establishment updated = repository.save(existing);
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
