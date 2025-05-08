package com.example.stageconnect.user.controller;

import com.example.stageconnect.user.dto.EstablishmentDto;
import com.example.stageconnect.user.dto.StudentDto;
import com.example.stageconnect.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/establishment")
public class EstablishmentController {

    @Qualifier("establishmentServiceImpl")
    private final UserService<EstablishmentDto> userService;

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentDto> readById(@PathVariable Long id) {
        EstablishmentDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<EstablishmentDto>> readAll() {
        List<EstablishmentDto> experiences = userService.findAll();
        return ResponseEntity.ok(experiences);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstablishmentDto> update(@PathVariable Long id, @RequestBody EstablishmentDto dto) {
        EstablishmentDto updated = userService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
