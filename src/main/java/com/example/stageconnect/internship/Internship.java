package com.example.stageconnect.internship;

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
@Table(name = "internship")
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String organization;

    private String role;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    private boolean current;

    @Column(nullable = false)
    private String description;

    private String organizationWebsite;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
