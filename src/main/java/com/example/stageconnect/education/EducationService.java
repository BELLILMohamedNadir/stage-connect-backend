package com.example.stageconnect.education;


import com.example.stageconnect.internship.InternshipDto;

import java.util.List;

public interface EducationService {
    EducationDto create(EducationDto dto);
    EducationDto findById(Long id);
    List<EducationDto> findAll();
    EducationDto update(Long id, EducationDto dto);
    void delete(Long id);
}
