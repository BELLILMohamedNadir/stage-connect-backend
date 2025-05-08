package com.example.stageconnect.language;

import com.example.stageconnect.internship.Internship;
import com.example.stageconnect.internship.InternshipDto;
import com.example.stageconnect.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper implements Mapper<Language, LanguageDto> {

    private ModelMapper mapper;

    public LanguageMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Language mapTo(LanguageDto internshipDto) {
        return mapper.map(internshipDto, Language.class);
    }

    @Override
    public LanguageDto mapFrom(Language internship) {
        return mapper.map(internship, LanguageDto.class);
    }

    @Override
    public void updateEntityFromDto(LanguageDto dto, Language entity) {
        mapper.map(dto, entity);
    }
}
