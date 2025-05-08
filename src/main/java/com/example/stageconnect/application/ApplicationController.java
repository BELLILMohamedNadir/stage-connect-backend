package com.example.stageconnect.application;


import com.example.stageconnect.certification.CertificationDto;
import com.example.stageconnect.certification.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationDto> create(@RequestBody ApplicationDto dto) {
        ApplicationDto created = applicationService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDto> readById(@PathVariable Long id) {
        ApplicationDto experience = applicationService.findById(id);
        return ResponseEntity.ok(experience);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDto>> readAll() {
        List<ApplicationDto> experiences = applicationService.findAll();
        return ResponseEntity.ok(experiences);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationDto> update(@PathVariable Long id, @RequestBody ApplicationDto dto) {
        ApplicationDto updated = applicationService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        applicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
