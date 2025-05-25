package com.example.stageconnect.offer;


import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonFormat;
import com.example.stageconnect.application.Application;
import com.example.stageconnect.user.model.Recruiter;
import com.example.stageconnect.user.model.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferDto {

    private Long id;

    @NotNull
    private String position;

    @NotNull
    private String website;

    @NotNull
    private String location;

    @NotNull
    private Long salaryStart;

    @NotNull
    private Long salaryEnd;

    @NotNull
    private String salaryUnit;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate postedDate;

    @NotNull
    private List<String> options;

    @NotNull
    private String experience;

    @NotNull
    private List<String> keySkills;

    @NotNull
    private String requirementSkills;

    @NotNull
    private String jobDescription;

    private String companyDescription;

    private String workType;

    private List<String> jobLevels;

    private List<String> employmentTypes;

    @NotNull
    private List<String> education;

    @NotNull
    private List<String> jobFunction;


    private Boolean isSaved;

    private Boolean isApplied;

    @NotNull
    private Long recruiterId;

    private String logo;

    private String company;


}