package com.example.stageconnect.user.controller;

import com.example.stageconnect.user.dto.RecruiterDto;
import com.example.stageconnect.user.dto.StudentDto;
import com.example.stageconnect.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruiter")
public class RecruiterController {


    @Qualifier("recruiterServiceImpl")
    private final UserService<RecruiterDto> userService;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
