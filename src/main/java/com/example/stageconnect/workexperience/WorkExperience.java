package com.example.stageconnect.workexperience;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "work_experience")
public class WorkExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(nullable = false)
    private boolean currentWorkHere;

    private String description;

    @Column(nullable = false)
    private String employmentType;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String jobLevel;

    @Column(nullable = false)
    private String jobFunction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private Student student;
}
