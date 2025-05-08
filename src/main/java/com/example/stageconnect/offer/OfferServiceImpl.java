package com.example.stageconnect.offer;

import com.example.stageconnect.internship.*;
import com.example.stageconnect.user.model.Recruiter;
import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository repository;
    private final OfferMapper mapper;
    private final UserRepository userRepository;

    @Override
    public OfferDto create(OfferDto dto) {
        Recruiter recruiter = (Recruiter) userRepository.findById(dto.getRecruiterId())
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        Offer entity = mapper.mapTo(dto);
        entity.setRecruiter(recruiter);

        entity.setId(null);

        entity.setPostedDate(LocalDate.now());
        //TODO CHANGE IT
        entity.setIsSaved(false);

        Offer saved = repository.save(entity);
        return mapper.mapFrom(saved);
    }

    @Override
    public OfferDto findById(Long id) {
        Offer entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found with id: " + id));
        return mapper.mapFrom(entity);
    }

    @Override
    public List<OfferDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public OfferDto update(Long id, OfferDto dto) {
        Offer existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found with id: " + id));

        mapper.updateEntityFromDto(dto, existing);
        Offer updated = repository.save(existing);
        return mapper.mapFrom(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Offer not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
