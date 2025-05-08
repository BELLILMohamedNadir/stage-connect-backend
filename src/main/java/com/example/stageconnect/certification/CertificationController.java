package com.example.stageconnect.certification;


import com.example.stageconnect.education.EducationDto;
import com.example.stageconnect.education.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/certification")
public class CertificationController {

    private final CertificationService certificationService;

    @PostMapping
    public ResponseEntity<CertificationDto> create(@RequestBody CertificationDto dto) {
        CertificationDto created = certificationService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificationDto> readById(@PathVariable Long id) {
        CertificationDto experience = certificationService.findById(id);
        return ResponseEntity.ok(experience);
    }

    @GetMapping
    public ResponseEntity<List<CertificationDto>> readAll() {
        List<CertificationDto> experiences = certificationService.findAll();
        return ResponseEntity.ok(experiences);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificationDto> update(@PathVariable Long id, @RequestBody CertificationDto dto) {
        CertificationDto updated = certificationService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        certificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
