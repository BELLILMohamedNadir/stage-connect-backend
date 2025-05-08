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
    private String location;

    @NotNull
    private Long salary;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate postedDate;

    @NotNull
    private List<String> options;

    @NotNull
    private String jobDescription;

    @NotNull
    private List<String> requirementSkills;

    @NotNull
    private String education;

    @NotNull
    private List<String> keySkills;

    @NotNull
    private String jobFunction;

    @NotNull
    private String employmentType;

    @NotNull
    private String website;

    private Boolean isSaved;

    @NotNull
    private Long recruiterId;
}
