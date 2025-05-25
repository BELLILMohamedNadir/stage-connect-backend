package com.example.stageconnect.offer;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonFormat;
import com.example.stageconnect.application.Application;
import com.example.stageconnect.user.model.Recruiter;
import com.example.stageconnect.user.model.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private String experience;

    private Long salaryStart;

    private Long salaryEnd;

    private String salaryUnit;

    private LocalDate postedDate;

    private String jobDescription;

    private String website;

    private String requirementSkills;

    private String companyDescription;

    private String workType;

    private Boolean isSaved;

    @ElementCollection
    @CollectionTable(name = "offer_options", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "option")
    private List<String> options;

    @ElementCollection
    @CollectionTable(name = "offer_job_level", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "jobLevels")
    private List<String> jobLevels;


    @ElementCollection
    @CollectionTable(name = "offer_employment_types", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "employmentTypes")
    private List<String> employmentTypes;

    @ElementCollection
    @CollectionTable(name = "offer_educations", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "education")
    private List<String> education;

    @ElementCollection
    @CollectionTable(name = "offer_job_functions", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "jobFunction")
    private List<String> jobFunction;


    @ElementCollection
    @CollectionTable(name = "offer_skills", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "skill")
    private List<String> keySkills;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;

    @ManyToMany(mappedBy = "offers")
    private List<Student> students;

    @ManyToMany(mappedBy = "savedOffers")
    private Set<Student> usersWhoSaved = new HashSet<>();

    @ManyToMany(mappedBy = "appliedOffer")
    private Set<Student> usersWhoApplied = new HashSet<>();
}