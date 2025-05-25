package com.example.stageconnect.user.dto;


import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonFormat;
import com.example.stageconnect.user.ROLE;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
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
public class EstablishmentDto {

    private Long id;

    @NotNull
    private String name;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;


    @Email(message = "add a valid email")
    private String email;


    private String phone;

    private String photo;

}
