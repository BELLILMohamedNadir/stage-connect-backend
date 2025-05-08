package com.example.stageconnect.offer;

import com.example.stageconnect.application.Application;
import com.example.stageconnect.user.model.Recruiter;
import com.example.stageconnect.user.model.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String position;

    private String location;

    private Long salary;

    private LocalDate postedDate;

    @ElementCollection
    @CollectionTable(name = "offer_options", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "option")
    private List<String> options;

    private String jobDescription;

    @ElementCollection
    @CollectionTable(name = "offer_requirement_skills", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "requirement_skills")
    private List<String> requirementSkills;

    private String education;

    @ElementCollection
    @CollectionTable(name = "offer_skills", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "skill")
    private List<String> keySkills;

    private String jobFunction;

    private String employmentType;

    private String website;

    private Boolean isSaved;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;

    @ManyToMany(mappedBy = "offers")
    private List<Student> students;
}
