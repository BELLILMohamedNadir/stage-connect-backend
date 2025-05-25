package com.example.stageconnect.offer;


import com.example.stageconnect.internship.InternshipDto;

import java.util.List;

public interface OfferService {
    OfferDto create(OfferDto dto);
    OfferDto findById(Long id);
    List<OfferDto> findAll(Long id);
    List<OfferDto> findAllByCompany(Long id);
    List<OfferDto> findAllSavedOffer(Long id);
    OfferDto update(Long id, OfferDto dto);
    void delete(Long id);
    void saveOffer(Long offerId, Long studentId);
    void unSaveOffer(Long offerId, Long studentId);
}
