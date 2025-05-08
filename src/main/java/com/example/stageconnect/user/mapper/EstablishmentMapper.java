package com.example.stageconnect.user.mapper;

import com.example.stageconnect.mappers.Mapper;
import com.example.stageconnect.user.dto.EstablishmentDto;
import com.example.stageconnect.user.dto.StudentDto;
import com.example.stageconnect.user.model.Establishment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EstablishmentMapper implements Mapper<Establishment, EstablishmentDto> {

    private ModelMapper mapper;

    public EstablishmentMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Establishment mapTo(EstablishmentDto dto) {
        return mapper.map(dto, Establishment.class);
    }

    @Override
    public EstablishmentDto mapFrom(Establishment user) {
        return mapper.map(user, EstablishmentDto.class);
    }

    @Override
    public void updateEntityFromDto(EstablishmentDto dto, Establishment entity) {
        mapper.map(dto, entity);
    }
}
