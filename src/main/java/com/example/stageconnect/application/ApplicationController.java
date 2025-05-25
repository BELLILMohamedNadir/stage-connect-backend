package com.example.stageconnect.application;


import com.example.stageconnect.auth.AuthDto;
import com.example.stageconnect.certification.CertificationDto;
import com.example.stageconnect.certification.CertificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationDto> create(@RequestPart("applicationDto") String applicationDto, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        // Convert the JSON string to a ApplicationDto object
        ApplicationDto dto = objectMapper.readValue(applicationDto, ApplicationDto.class);

        ApplicationDto created = applicationService.create(dto, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDto> readById(@PathVariable Long id) {
        ApplicationDto applicationDto = applicationService.findById(id);
        return ResponseEntity.ok(applicationDto);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDto>> readAll() {
        List<ApplicationDto> applicationDtos = applicationService.findAll();
        return ResponseEntity.ok(applicationDtos);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<ApplicationDto>> getStudentApplications(@PathVariable Long id) {
        List<ApplicationDto> applications = applicationService.getStudentApplications(id);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/recruiter/{id}")
    public ResponseEntity<List<ApplicationDto>> getRecruiterApplications(@PathVariable Long id) {
        List<ApplicationDto> applications = applicationService.getRecruiterApplications(id);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/establishment/{id}")
    public ResponseEntity<List<ApplicationDto>> getEstablishmentApplications(@PathVariable Long id) {
        List<ApplicationDto> applications = applicationService.getEstablishmentApplications(id);
        return ResponseEntity.ok(applications);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationDto> update(@PathVariable Long id, @RequestBody ApplicationDto dto) {
        ApplicationDto updated = applicationService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{applicationId}/{studentId}")
    public ResponseEntity<Void> delete(@PathVariable("applicationId") Long applicationId, @PathVariable("studentId") Long studentId) {
        applicationService.delete(applicationId, studentId);
        return ResponseEntity.noContent().build();
    }
}
