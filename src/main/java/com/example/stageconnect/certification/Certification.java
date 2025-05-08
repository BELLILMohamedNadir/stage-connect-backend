package com.example.stageconnect.certification;

import com.example.stageconnect.user.model.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "certification")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String organization;

    @Column(nullable = false)
    private LocalDate dateOfIssue;

    private LocalDate expirationDate;

    private boolean credentialWillNotExpire;

    private String credentialId;

    private String credentialUrl;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
