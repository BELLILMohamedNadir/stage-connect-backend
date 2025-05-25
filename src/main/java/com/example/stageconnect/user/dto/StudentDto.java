package com.example.stageconnect.user.dto;


import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
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
public class StudentDto {

    private Long id;

    private String name;

    private String firstName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Email(message = "add a valid email")
    private String email;

    private String phone;

    private String photo;

    private String resume;

    private String gender;

    private String summary;

    private String address;

    private String currentPosition;

    private List<String> skills;
}
