package com.example.stageconnect.project;

import com.example.stageconnect.user.model.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String role;


    @Column(nullable = false)
    private String startDate;

    private String endDate;

    private boolean current;

    @Column(nullable = false)
    private String description;

    private String projectUrl;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
