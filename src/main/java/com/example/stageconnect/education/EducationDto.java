package com.example.stageconnect.education;


import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String from;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String to;

    private boolean graduated;

    @NotNull
    private Float gpa;

    @NotNull
    private Float total;

    private String description;

    @NotNull
    private Long userId;
}
