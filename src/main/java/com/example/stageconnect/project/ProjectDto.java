package com.example.stageconnect.project;


import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
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
public class ProjectDto {

    private Long id;

    @NotNull
    private String projectName;

    @NotNull
    private String role;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String from;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String to;

    private boolean current;

    @NotNull
    private String description;

    private String projectUrl;

    @NotNull
    private Long userId;
}
