package com.example.stageconnect.offer;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/offer")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ResponseEntity<OfferDto> create(@RequestBody OfferDto dto) {
        OfferDto created = offerService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> readById(@PathVariable Long id) {
        OfferDto experience = offerService.findById(id);
        return ResponseEntity.ok(experience);
    }

    @GetMapping
    public ResponseEntity<List<OfferDto>> readAll() {
        List<OfferDto> experiences = offerService.findAll();
        return ResponseEntity.ok(experiences);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferDto> update(@PathVariable Long id, @RequestBody OfferDto dto) {
        OfferDto updated = offerService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        offerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
