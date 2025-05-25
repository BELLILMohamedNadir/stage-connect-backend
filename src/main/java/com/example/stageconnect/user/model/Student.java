package com.example.stageconnect.user.model;

import com.example.stageconnect.application.Application;
import com.example.stageconnect.certification.Certification;
import com.example.stageconnect.education.Education;
import com.example.stageconnect.file.File;
import com.example.stageconnect.internship.Internship;
import com.example.stageconnect.language.Language;
import com.example.stageconnect.messaging.room.Room;
import com.example.stageconnect.offer.Offer;
import com.example.stageconnect.project.Project;
import com.example.stageconnect.workexperience.WorkExperience;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student extends BaseUser{


    @Column(nullable = false)
    private  String gender;


    @ElementCollection
    @CollectionTable(name = "student_skills", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "skill")
    private List<String> skills;

    @ElementCollection
    @CollectionTable(name = "student_expertises", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "expertise")
    private List<String> expertises;

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
    private List<Room> rooms;

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

    @ManyToMany
    @JoinTable(
            name = "saved_offers",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id")
    )
    private Set<Offer> savedOffers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "applied_offers",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id")
    )
    private Set<Offer> appliedOffer = new HashSet<>();

    public void addSavedOffer(Offer offer) {
        this.savedOffers.add(offer);
    }

    public void removeSavedOffer(Offer offer) {
        this.savedOffers.remove(offer);
    }

    public void addAppliedOffer(Offer offer) {
        this.appliedOffer.add(offer);
    }

    public void removeAppliedOffer(Offer offer) {
        this.appliedOffer.remove(offer);
    }
}
