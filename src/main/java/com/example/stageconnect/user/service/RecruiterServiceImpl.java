package com.example.stageconnect.user.service;


import com.example.stageconnect.file.FileService;
import com.example.stageconnect.user.dto.RecruiterDto;
import com.example.stageconnect.user.dto.StudentDto;
import com.example.stageconnect.user.mapper.RecruiterMapper;
import com.example.stageconnect.user.model.Recruiter;
import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.EstablishmentRepository;
import com.example.stageconnect.user.repository.RecruiterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RecruiterServiceImpl implements UserService <RecruiterDto> {

    private final RecruiterRepository repository;
    private final RecruiterMapper mapper;
    private final FileService fileService;


    @Override
    public RecruiterDto findById(Long id) {
        Recruiter entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return mapper.mapFrom(entity);
    }

    @Override
    public List<RecruiterDto> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> {
                    if (entity != null) {
                        return mapper.mapFrom((Recruiter) entity);
                    } else {
                        throw new EntityNotFoundException("Unknown user type");
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public RecruiterDto update(Long id, RecruiterDto dto) {
        Recruiter existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        mapper.updateEntityFromDto(dto, existing);
        Recruiter updated = repository.save(existing);
        return mapper.mapFrom(updated);
    }

    public RecruiterDto update(RecruiterDto dto, MultipartFile file) throws IOException {
        Recruiter existing = repository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Recruiter not found with id: " + dto.getId()));

        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getAddress() != null) existing.setAddress(dto.getAddress());
        if (dto.getCurrentPosition() != null) existing.setCurrentPosition(dto.getCurrentPosition());
        if (dto.getSummary() != null) existing.setSummary(dto.getSummary());
        if (dto.getDateOfBirth() != null) existing.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getEmail() != null) existing.setEmail(dto.getEmail());
        if (dto.getPhone() != null) existing.setPhone(dto.getPhone());
        if (dto.getOrganizationName() != null) existing.setOrganizationName(dto.getOrganizationName());

        String photoName = null;
        if (file != null) {
            photoName = fileService.saveFile(file, dto.getId());
            existing.setPhoto(photoName);
        }

        Recruiter updated = repository.save(existing);
        RecruiterDto updatedDto = mapper.mapFrom(updated);

        if (photoName != null) {updatedDto.setPhoto(fileService.generateFileUrl(photoName));}
        return updatedDto;
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Education not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
