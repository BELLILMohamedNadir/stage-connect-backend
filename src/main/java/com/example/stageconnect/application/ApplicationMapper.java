package com.example.stageconnect.application;

import com.example.stageconnect.certification.Certification;
import com.example.stageconnect.certification.CertificationDto;
import com.example.stageconnect.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper implements Mapper<Application, ApplicationDto> {

    private ModelMapper mapper;

    public ApplicationMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Application mapTo(ApplicationDto applicationDto) {
        return mapper.map(applicationDto, Application.class);
    }

    @Override
    public ApplicationDto mapFrom(Application application) {
        return mapper.map(application, ApplicationDto.class);
    }

    @Override
    public void updateEntityFromDto(ApplicationDto dto, Application entity) {
        mapper.map(dto, entity);
    }
}
