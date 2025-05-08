package com.example.stageconnect.workexperience;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/work-experiences")
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;

    @PostMapping
    public ResponseEntity<WorkExperienceDto> create(@RequestBody WorkExperienceDto dto) {
        WorkExperienceDto created = workExperienceService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkExperienceDto> readById(@PathVariable Long id) {
        WorkExperienceDto experience = workExperienceService.findById(id);
        return ResponseEntity.ok(experience);
    }

    @GetMapping
    public ResponseEntity<List<WorkExperienceDto>> readAll() {
        List<WorkExperienceDto> experiences = workExperienceService.findAll();
        return ResponseEntity.ok(experiences);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkExperienceDto> update(@PathVariable Long id, @RequestBody WorkExperienceDto dto) {
        WorkExperienceDto updated = workExperienceService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workExperienceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
