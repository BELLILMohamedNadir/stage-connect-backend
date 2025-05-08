package com.example.stageconnect.education;


import com.example.stageconnect.internship.InternshipDto;
import com.example.stageconnect.internship.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationService;

    @PostMapping
    public ResponseEntity<EducationDto> create(@RequestBody EducationDto dto) {
        EducationDto created = educationService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationDto> readById(@PathVariable Long id) {
        EducationDto experience = educationService.findById(id);
        return ResponseEntity.ok(experience);
    }

    @GetMapping
    public ResponseEntity<List<EducationDto>> readAll() {
        List<EducationDto> experiences = educationService.findAll();
        return ResponseEntity.ok(experiences);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationDto> update(@PathVariable Long id, @RequestBody EducationDto dto) {
        EducationDto updated = educationService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        educationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
