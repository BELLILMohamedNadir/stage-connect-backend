package com.example.stageconnect.user.controller;

import com.example.stageconnect.user.dto.RecruiterDto;
import com.example.stageconnect.user.dto.StudentDto;
import com.example.stageconnect.user.service.RecruiterServiceImpl;
import com.example.stageconnect.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruiter")
@Slf4j
public class RecruiterController {


    private final RecruiterServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity<RecruiterDto> readById(@PathVariable Long id) {
        RecruiterDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<RecruiterDto>> readAll() {
        List<RecruiterDto> experiences = userService.findAll();
        return ResponseEntity.ok(experiences);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecruiterDto> update(@PathVariable Long id, @RequestBody RecruiterDto dto) {
        RecruiterDto updated = userService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PostMapping
    public ResponseEntity<RecruiterDto> update(@RequestPart("recruiterDto") String recruiterDtoJson, @RequestParam("file") MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        RecruiterDto recruiterDto = objectMapper.readValue(recruiterDtoJson, RecruiterDto.class);
        RecruiterDto updated = userService.update(recruiterDto, file);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
