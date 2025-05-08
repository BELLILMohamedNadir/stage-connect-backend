package com.example.stageconnect.certification;


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
public class CertificationDto {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String organization;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfIssue;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    private boolean credentialWillNotExpire;

    private String credentialId;

    private String credentialUrl;

    @NotNull
    private Long userId;
}
