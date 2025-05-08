package com.example.stageconnect.language;


import com.example.stageconnect.internship.InternshipDto;

import java.util.List;

public interface LanguageService {
    LanguageDto create(LanguageDto dto);
    LanguageDto findById(Long id);
    List<LanguageDto> findAll();
    LanguageDto update(Long id, LanguageDto dto);
    void delete(Long id);
}
