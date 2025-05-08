package com.example.stageconnect.certification;


import com.example.stageconnect.education.EducationDto;

import java.util.List;

public interface CertificationService {
    CertificationDto create(CertificationDto dto);
    CertificationDto findById(Long id);
    List<CertificationDto> findAll();
    CertificationDto update(Long id, CertificationDto dto);
    void delete(Long id);
}
