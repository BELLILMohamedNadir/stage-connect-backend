package com.example.stageconnect.application;


import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonFormat;
import com.example.stageconnect.certification.Certification;
import com.example.stageconnect.certification.CertificationDto;
import com.example.stageconnect.education.Education;
import com.example.stageconnect.education.EducationDto;
import com.example.stageconnect.offer.OfferDto;
import com.example.stageconnect.project.Project;
import com.example.stageconnect.project.ProjectDto;
import com.example.stageconnect.workexperience.WorkExperience;
import com.example.stageconnect.workexperience.WorkExperienceDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationDto {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private STATUS recruiterStatus;

    @Enumerated(EnumType.STRING)
    private STATUS establishmentStatus;

    @Enumerated(EnumType.STRING)
    private STATUS status;

    private Long offerId;

    private Long studentId;

    private String studentName;

    private String logoUrl;

    private String resumeUrl;

    private String summary;

    private List<EducationDto> educations;

    private List<WorkExperienceDto> workExperiences;

    private List<CertificationDto> certifications;

    private List<ProjectDto> projects;

    private OfferDto offerDto;

    private String coverLetter;
}
