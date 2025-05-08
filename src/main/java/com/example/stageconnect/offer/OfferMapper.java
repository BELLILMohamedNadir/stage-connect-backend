package com.example.stageconnect.offer;

import com.example.stageconnect.internship.Internship;
import com.example.stageconnect.internship.InternshipDto;
import com.example.stageconnect.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper implements Mapper<Offer, OfferDto> {

    private ModelMapper mapper;

    public OfferMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Offer mapTo(OfferDto offerDto) {
        return mapper.map(offerDto, Offer.class);
    }

    @Override
    public OfferDto mapFrom(Offer offer) {
        return mapper.map(offer, OfferDto.class);
    }

    @Override
    public void updateEntityFromDto(OfferDto dto, Offer entity) {
        mapper.map(dto, entity);
    }
}
