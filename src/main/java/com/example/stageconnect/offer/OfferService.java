package com.example.stageconnect.offer;


import com.example.stageconnect.internship.InternshipDto;

import java.util.List;

public interface OfferService {
    OfferDto create(OfferDto dto);
    OfferDto findById(Long id);
    List<OfferDto> findAll();
    OfferDto update(Long id, OfferDto dto);
    void delete(Long id);
}
