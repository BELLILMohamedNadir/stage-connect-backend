package com.example.stageconnect.offer;

import com.example.stageconnect.file.FileService;
import com.example.stageconnect.user.model.Recruiter;
import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.RecruiterRepository;
import com.example.stageconnect.user.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper mapper;
    private final RecruiterRepository recruiterRepository;
    private final StudentRepository studentRepository;
    private final FileService fileService;

    @Override
    public OfferDto create(OfferDto dto) {
        Recruiter recruiter = (Recruiter) recruiterRepository.findById(dto.getRecruiterId())
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        Offer entity = mapper.mapTo(dto);
        entity.setRecruiter(recruiter);

        entity.setId(null);

        entity.setPostedDate(LocalDate.now());
        //TODO CHANGE IT
        entity.setIsSaved(false);

        Offer saved = offerRepository.save(entity);
        return mapper.mapFrom(saved);
    }

    @Override
    public OfferDto findById(Long id) {
        Offer entity = offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found with id: " + id));
        OfferDto dto =  mapper.mapFrom(entity);
        dto.setLogo(fileService.generateFileUrl(entity.getRecruiter().getPhoto()));
        dto.setCompany(entity.getRecruiter().getName());
        dto.setCompanyDescription(entity.getRecruiter().getSummary());
        return dto;
    }

    @Override
    public List<OfferDto> findAll(Long id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return offerRepository.findAll()
                .stream()
                .map(entity -> {
                    OfferDto dto = mapper.mapFrom(entity);
                    dto.setLogo(fileService.generateFileUrl(entity.getRecruiter().getPhoto()));
                    dto.setCompany(entity.getRecruiter().getName());
                    dto.setCompanyDescription(entity.getRecruiter().getSummary());
                    boolean isSaved = entity.getUsersWhoSaved()
                            .stream()
                            .anyMatch(user -> user.getId().equals(id));
                    dto.setIsSaved(isSaved);

                    boolean isApplied = entity.getUsersWhoApplied()
                            .stream()
                            .anyMatch(user -> user.getId().equals(id));
                    dto.setIsApplied(isApplied);

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferDto> findAllByCompany(Long id) {
        recruiterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));
        return offerRepository.findAll()
                .stream()
                .filter(it -> it.getRecruiter().getId().equals(id))
                .map(entity -> {
                    OfferDto dto = mapper.mapFrom(entity);
                    dto.setLogo(fileService.generateFileUrl(entity.getRecruiter().getPhoto()));
                    dto.setCompany(entity.getRecruiter().getName());
//                    dto.setCompanyDescription(entity.getRecruiter().getSummary());
                    boolean isSaved = entity.getUsersWhoSaved()
                            .stream()
                            .anyMatch(user -> user.getId().equals(id));
                    dto.setIsSaved(isSaved);

                    boolean isApplied = entity.getUsersWhoApplied()
                            .stream()
                            .anyMatch(user -> user.getId().equals(id));
                    dto.setIsApplied(isApplied);

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferDto> findAllSavedOffer(Long id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return offerRepository.findAll()
                .stream()
                .map(entity -> {
                    OfferDto dto = mapper.mapFrom(entity);
                    dto.setLogo(fileService.generateFileUrl(entity.getRecruiter().getPhoto()));
                    dto.setCompany(entity.getRecruiter().getName());
                    dto.setCompanyDescription(entity.getRecruiter().getSummary());
                    boolean isSaved = entity.getUsersWhoSaved()
                            .stream()
                            .anyMatch(user -> user.getId().equals(id));
                    dto.setIsSaved(isSaved);

                    boolean isApplied = entity.getUsersWhoApplied()
                            .stream()
                            .anyMatch(user -> user.getId().equals(id));
                    dto.setIsApplied(isApplied);

                    return dto;
                })
                .filter(OfferDto::getIsSaved)
                .collect(Collectors.toList());
    }

    @Override
    public OfferDto update(Long id, OfferDto dto) {
        Offer existing = offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found with id: " + id));

        existing.setEducation(dto.getEducation());
        existing.setKeySkills(dto.getKeySkills());
        existing.setExperience(dto.getExperience());
        existing.setJobDescription(dto.getJobDescription());
        existing.setLocation(dto.getLocation());
        existing.setJobLevels(dto.getJobLevels());
        existing.setJobFunction(dto.getJobFunction());
        existing.setRequirementSkills(dto.getRequirementSkills());
        existing.setOptions(dto.getOptions());
        existing.setEmploymentTypes(dto.getEmploymentTypes());
        existing.setWorkType(dto.getWorkType());
        existing.setSalaryStart(dto.getSalaryStart());
        existing.setSalaryEnd(dto.getSalaryEnd());
        existing.setSalaryUnit(dto.getSalaryUnit());
        existing.setWebsite(dto.getWebsite());
        existing.setPosition(dto.getPosition());
        existing.setCompanyDescription(dto.getCompanyDescription());
        Offer updated = offerRepository.save(existing);
        return mapper.mapFrom(updated);
    }

    @Override
    public void delete(Long id) {
        if (!offerRepository.existsById(id)) {
            throw new EntityNotFoundException("Offer not found with id: " + id);
        }
        offerRepository.deleteById(id);
    }

    @Override
    public void saveOffer(Long offerId, Long studentId) {
        Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new EntityNotFoundException("Offer not found with id: " + offerId));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        student.addSavedOffer(offer);
        studentRepository.save(student);
    }

    @Override
    public void unSaveOffer(Long offerId, Long studentId) {
        Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new EntityNotFoundException("Offer not found with id: " + offerId));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        student.removeSavedOffer(offer);
        studentRepository.save(student);
    }
}
