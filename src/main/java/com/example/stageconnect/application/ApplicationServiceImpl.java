package com.example.stageconnect.application;

import com.example.stageconnect.certification.Certification;
import com.example.stageconnect.certification.CertificationMapper;
import com.example.stageconnect.education.EducationMapper;
import com.example.stageconnect.file.FileService;
import com.example.stageconnect.offer.Offer;
import com.example.stageconnect.offer.OfferMapper;
import com.example.stageconnect.offer.OfferRepository;
import com.example.stageconnect.project.ProjectMapper;
import com.example.stageconnect.user.model.Establishment;
import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.EstablishmentRepository;
import com.example.stageconnect.user.repository.StudentRepository;
import com.example.stageconnect.user.repository.UserRepository;
import com.example.stageconnect.workexperience.WorkExperienceMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository repository;
    private final ApplicationMapper mapper;
    private final OfferMapper offerMapper;
    private final WorkExperienceMapper workExperienceMapper;
    private final ProjectMapper projectMapper;
    private final CertificationMapper certificationMapper;
    private final EducationMapper educationMapper;
    private final StudentRepository studentRepository;
    private final EstablishmentRepository establishmentRepository;
    private final OfferRepository offerRepository;
    private final FileService fileService;
    private final UserRepository userRepository;


    @Override
    public ApplicationDto create(ApplicationDto dto, MultipartFile file) throws IOException {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Establishment establishment = establishmentRepository.findById(student.getEstablishment().getId())
                .orElseThrow(() -> new RuntimeException("Establishment not found"));

        Offer offer = offerRepository.findById(dto.getOfferId())
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        Application entity = mapper.mapTo(dto);
        entity.setStudent(student);
        entity.setEstablishment(establishment);
        entity.setOffer(offer);
        entity.setRecruiterStatus(STATUS.SENT);
        entity.setEstablishmentStatus(STATUS.SENT);
        entity.setDate(LocalDate.now());
        entity.setVerified(false);

        if (file != null && !file.isEmpty()) {
            String resumePath = fileService.saveFile(file, student.getId());
            entity.setResumePath(resumePath);
        } else {
            throw new IllegalArgumentException("Resume file is required.");
        }

        log.info("Created application: {}", entity);
        Application saved = repository.save(entity);

        student.addAppliedOffer(offer);
        studentRepository.save(student);


        return mapper.mapFrom(saved);
    }


    @Override
    public ApplicationDto findById(Long id) {
        Application entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application not found with id: " + id));

        ApplicationDto dto = mapper.mapFrom(entity);
        dto.setResumeUrl(fileService.generateFileUrl(entity.getResumePath()));
        dto.setLogoUrl(fileService.generateFileUrl(entity.getOffer().getRecruiter().getPhoto()));
        dto.setOfferDto(offerMapper.mapFrom(entity.getOffer()));
        return dto;
    }

    @Override
    public List<ApplicationDto> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> {
                    ApplicationDto dto = mapper.mapFrom(entity);
                    dto.setResumeUrl(fileService.generateFileUrl(entity.getResumePath()));
                    dto.setLogoUrl(fileService.generateFileUrl(entity.getOffer().getRecruiter().getPhoto()));
                    dto.setOfferDto(offerMapper.mapFrom(entity.getOffer()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDto> getStudentApplications(Long id) {
        return repository.findByStudentId(id)
                .stream()
                .map(entity -> {
                    ApplicationDto dto = mapper.mapFrom(entity);
                    dto.setResumeUrl(fileService.generateFileUrl(entity.getResumePath()));
                    dto.setLogoUrl(fileService.generateFileUrl(entity.getOffer().getRecruiter().getPhoto()));
                    dto.setOfferDto(offerMapper.mapFrom(entity.getOffer()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDto> getRecruiterApplications(Long id) {
        return repository.findByRecruiterId(id)
                .stream()
                .map(entity -> {
                    ApplicationDto dto = mapper.mapFrom(entity);
                    dto.setWorkExperiences(entity.getStudent().getWorkExperiences().stream().map(workExperienceMapper::mapFrom).collect(Collectors.toList()));
                    dto.setSummary(entity.getStudent().getSummary());
                    dto.setEducations(entity.getStudent().getEducations().stream().map(educationMapper::mapFrom).collect(Collectors.toList()));
                    dto.setCertifications(entity.getStudent().getCertifications().stream().map(certificationMapper::mapFrom).collect(Collectors.toList()));
                    dto.setProjects(entity.getStudent().getProjects().stream().map(projectMapper::mapFrom).collect(Collectors.toList()));
                    dto.setResumeUrl(entity.getResumePath());
                    dto.setLogoUrl(fileService.generateFileUrl(entity.getStudent().getPhoto()));
                    dto.setOfferDto(offerMapper.mapFrom(entity.getOffer()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDto> getEstablishmentApplications(Long id) {
        return repository.findByEstablishmentId(id)
                .stream()
                .filter(entity -> entity.getRecruiterStatus() == STATUS.ACCEPTED)
                .map(entity -> {
                    ApplicationDto dto = mapper.mapFrom(entity);
                    dto.setResumeUrl(fileService.generateFileUrl(entity.getResumePath()));
                    dto.setLogoUrl(fileService.generateFileUrl(entity.getOffer().getRecruiter().getPhoto()));
                    dto.setOfferDto(offerMapper.mapFrom(entity.getOffer()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDto update(Long id, ApplicationDto dto) {
        Application existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application not found with id: " + id));

        existing.setRecruiterStatus(dto.getRecruiterStatus());
        existing.setEstablishmentStatus(dto.getEstablishmentStatus());

        if (dto.getEstablishmentStatus() == STATUS.ACCEPTED)
            existing.setStatus(STATUS.ACCEPTED);
        else{
            if (dto.getEstablishmentStatus() == STATUS.REJECTED || dto.getRecruiterStatus() == STATUS.REJECTED)
                existing.setStatus(STATUS.REJECTED);
            else
                existing.setStatus(STATUS.SENT);
        }

        Application updated = repository.save(existing);
        return mapper.mapFrom(updated);
    }

    @Override
    public void delete(Long applicationId, Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        Application application = repository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Application not found with id: " + applicationId));
        student.removeAppliedOffer(application.getOffer());
        repository.delete(application);
    }
}
