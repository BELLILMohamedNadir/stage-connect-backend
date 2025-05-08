package com.example.stageconnect.auth.helper;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String firstName;

    @NotNull
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

    @NotNull
    private String token;
}
