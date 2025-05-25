package com.example.stageconnect.education;


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
public class EducationDto {

    private Long id;

    @NotNull
    private String education;

    @NotNull
    private String course;

    @NotNull
    private String university;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private boolean graduated;

    @NotNull
    private Float gpa;

    @NotNull
    private Float total;

    private String description;

    @NotNull
    private Long userId;
}
