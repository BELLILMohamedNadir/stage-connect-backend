package com.example.stageconnect.internship;


import com.example.stageconnect.workexperience.WorkExperienceDto;
import com.example.stageconnect.workexperience.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/internship")
public class InternshipController {

    private final InternshipService internshipService;

    @PostMapping
    public ResponseEntity<InternshipDto> create(@RequestBody InternshipDto dto) {
        InternshipDto created = internshipService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternshipDto> readById(@PathVariable Long id) {
        InternshipDto experience = internshipService.findById(id);
        return ResponseEntity.ok(experience);
    }

    @GetMapping
    public ResponseEntity<List<InternshipDto>> readAll() {
        List<InternshipDto> experiences = internshipService.findAll();
        return ResponseEntity.ok(experiences);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternshipDto> update(@PathVariable Long id, @RequestBody InternshipDto dto) {
        InternshipDto updated = internshipService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        internshipService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
