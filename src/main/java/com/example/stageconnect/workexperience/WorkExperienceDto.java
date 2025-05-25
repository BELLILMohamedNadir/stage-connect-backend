package com.example.stageconnect.workexperience;


import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkExperienceDto {

    private Long id;

    @NotNull
    private String jobTitle;

    @NotNull
    private String company;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull
    private boolean currentWorkHere;

    private String description;

    @NotNull
    private String employmentType;

    @NotNull
    private String location;

    @NotNull
    private String jobLevel;

    @NotNull
    private String jobFunction;

    @NotNull
    private Long userId;
}
