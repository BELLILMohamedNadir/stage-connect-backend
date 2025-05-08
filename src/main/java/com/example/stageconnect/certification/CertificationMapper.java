package com.example.stageconnect.certification;

import com.example.stageconnect.education.Education;
import com.example.stageconnect.education.EducationDto;
import com.example.stageconnect.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CertificationMapper implements Mapper<Certification, CertificationDto> {

    private ModelMapper mapper;

    public CertificationMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Certification mapTo(CertificationDto certificationDto) {
        return mapper.map(certificationDto, Certification.class);
    }

    @Override
    public CertificationDto mapFrom(Certification certification) {
        return mapper.map(certification, CertificationDto.class);
    }

    @Override
    public void updateEntityFromDto(CertificationDto dto, Certification entity) {
        mapper.map(dto, entity);
    }
}
