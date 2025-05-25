package com.example.stageconnect.application;


import com.example.stageconnect.certification.CertificationDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ApplicationService {
    ApplicationDto create(ApplicationDto dto, MultipartFile file) throws IOException;
    ApplicationDto findById(Long id);
    List<ApplicationDto> findAll();
    List<ApplicationDto> getStudentApplications(Long id);
    List<ApplicationDto> getRecruiterApplications(Long id);
    List<ApplicationDto> getEstablishmentApplications(Long id);
    ApplicationDto update(Long id, ApplicationDto dto);
    void delete(Long applicationId, Long studentId);
}
