package com.example.stageconnect.application;

import com.example.stageconnect.offer.Offer;
import com.example.stageconnect.offer.OfferRepository;
import com.example.stageconnect.user.model.Establishment;
import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository repository;
    private final ApplicationMapper mapper;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;


    @Override
    public ApplicationDto create(ApplicationDto dto) {
        Student student = (Student) userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Establishment establishment = (Establishment) userRepository.findById(student.getEstablishment().getId())
                .orElseThrow(() -> new RuntimeException("Establishment not found"));
        Offer offer = offerRepository.findById(dto.getOfferId())
                .orElseThrow(() -> new RuntimeException("Offer not found"));


        Application entity = mapper.mapTo(dto);
        entity.setStudent(student);
        entity.setEstablishment(establishment);
        entity.setOffer(offer);
        entity.setStatus(Status.SENT);
        entity.setDate(LocalDate.now());
        entity.setVerified(false);


        entity.setId(null);

        Application saved = repository.save(entity);
        return mapper.mapFrom(saved);
    }

    @Override
    public ApplicationDto findById(Long id) {
        Application entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application not found with id: " + id));
        return mapper.mapFrom(entity);
    }

    @Override
    public List<ApplicationDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDto update(Long id, ApplicationDto dto) {
        Application existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application not found with id: " + id));

        existing.setStatus(dto.getStatus());

        Application updated = repository.save(existing);
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
