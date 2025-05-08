package com.example.stageconnect.language;


import com.example.stageconnect.internship.InternshipDto;
import com.example.stageconnect.internship.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/language")
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping
    public ResponseEntity<LanguageDto> create(@RequestBody LanguageDto dto) {
        LanguageDto created = languageService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageDto> readById(@PathVariable Long id) {
        LanguageDto experience = languageService.findById(id);
        return ResponseEntity.ok(experience);
    }

    @GetMapping
    public ResponseEntity<List<LanguageDto>> readAll() {
        List<LanguageDto> experiences = languageService.findAll();
        return ResponseEntity.ok(experiences);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LanguageDto> update(@PathVariable Long id, @RequestBody LanguageDto dto) {
        LanguageDto updated = languageService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        languageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
