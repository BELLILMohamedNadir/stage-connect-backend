package com.example.stageconnect.user.controller;

import com.example.stageconnect.auth.AuthDto;
import com.example.stageconnect.user.dto.StudentDto;
import com.example.stageconnect.user.service.StudentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    private final StudentServiceImpl studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> readById(@PathVariable Long id) {
        StudentDto userDto = studentService.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> readAll() {
        List<StudentDto> experiences = studentService.findAll();
        return ResponseEntity.ok(experiences);
    }

    @PostMapping("/skills/{studentId}")
    public ResponseEntity<List<String>> addSkills(@PathVariable Long studentId, @RequestBody List<String> skills) {
        return ResponseEntity.ok(studentService.addSkills(studentId, skills));
    }

    @PostMapping
    public ResponseEntity<StudentDto> update(@RequestPart("studentDto") String studentDtoJson, @RequestParam("file") MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        StudentDto studentDto = objectMapper.readValue(studentDtoJson, StudentDto.class);

        StudentDto updated = studentService.update(studentDto, file);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
