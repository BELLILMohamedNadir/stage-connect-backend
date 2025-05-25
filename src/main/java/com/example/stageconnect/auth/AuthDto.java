package com.example.stageconnect.auth;


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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthDto {

    @NotNull
    private String name;

    private String firstName;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull
    @Email(message = "add a valid email")
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ROLE role;

    @NotNull
    private String phone;

    private String photo;

    private String gender;

    private Long establishmentId;

    private List<String> expertises;
}
