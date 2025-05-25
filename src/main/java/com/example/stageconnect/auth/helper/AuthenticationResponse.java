package com.example.stageconnect.auth.helper;


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
public class AuthenticationResponse {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String firstName;

    private LocalDate dateOfBirth;

    private String address;

    @NotNull
    private String email;

    @NotNull
    private String role;

    @NotNull
    private String phone;

    @NotNull
    private String gender;

    private String photo;

    private String resume;

    private String currentPosition;

    @NotNull
    private String token;
}