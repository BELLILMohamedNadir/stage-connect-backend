package com.example.stageconnect.user.model;

import com.example.stageconnect.application.Application;
import com.example.stageconnect.certification.Certification;
import com.example.stageconnect.education.Education;
import com.example.stageconnect.file.File;
import com.example.stageconnect.internship.Internship;
import com.example.stageconnect.language.Language;
import com.example.stageconnect.chat.room.Room;
import com.example.stageconnect.offer.Offer;
import com.example.stageconnect.project.Project;
import com.example.stageconnect.workexperience.WorkExperience;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student extends BaseUser{

    @Column(nullable = false)
    private  String firstName;

    @Column(nullable = false)
    private  String gender;

    private  String currentPosition;

    @ElementCollection
    @CollectionTable(name = "user_skills", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "skill")
    private List<String> skills;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private  List<File> files;


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private  List<WorkExperience> workExperiences;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private  List<Education> educations;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private  List<Project> projects;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private  List<Certification> certifications;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private  List<Internship> internships;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private  List<Language> languages;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> messages;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;

    @ManyToMany
    @JoinTable(
            name = "student_offer",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id")
    )
    private List<Offer> offers;

    @ManyToOne
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;
}
