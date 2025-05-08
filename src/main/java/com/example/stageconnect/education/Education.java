package com.example.stageconnect.education;

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
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String education;

    @Column(nullable = false)
    private String course;

    @Column(nullable = false)
    private String university;

    @Column(nullable = false, name = "start_date")
    private LocalDate from;

    @Column(name = "end_date")
    private LocalDate to;

    private boolean graduated;

    @Column(nullable = false)
    private Float gpa;

    @Column(nullable = false)
    private Float total;

    private String description;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
