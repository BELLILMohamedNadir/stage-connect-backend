package com.example.stageconnect.application;


import com.example.stageconnect.certification.CertificationDto;

import java.util.List;

public interface ApplicationService {
    ApplicationDto create(ApplicationDto dto);
    ApplicationDto findById(Long id);
    List<ApplicationDto> findAll();
    ApplicationDto update(Long id, ApplicationDto dto);
    void delete(Long id);
}
