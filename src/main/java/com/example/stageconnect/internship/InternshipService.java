package com.example.stageconnect.internship;


import com.example.stageconnect.workexperience.WorkExperienceDto;

import java.util.List;

public interface InternshipService {
    InternshipDto create(InternshipDto dto);
    InternshipDto findById(Long id);
    List<InternshipDto> findAll();
    InternshipDto update(Long id, InternshipDto dto);
    void delete(Long id);
}
