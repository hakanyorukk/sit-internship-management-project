package org.example.internship_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.internship_system.entity.enums.ApplicationStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentProfile student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "internship_offer_id")
    private InternshipOffer internshipOffer;

    private LocalDateTime applicationDate;

    @Column(length = 2000)
    private String motivationLetter;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    @Column(length = 1000)
    private String comment;


    public Application() {}

}
