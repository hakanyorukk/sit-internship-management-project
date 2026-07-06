package org.example.internship_system.entity;

import jakarta.persistence.*;
import org.example.internship_system.entity.enums.ApplicationStatus;

import java.time.LocalDateTime;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentProfile getStudent() {
        return student;
    }

    public void setStudent(StudentProfile student) {
        this.student = student;
    }

    public InternshipOffer getInternshipOffer() {
        return internshipOffer;
    }

    public void setInternshipOffer(InternshipOffer internshipOffer) {
        this.internshipOffer = internshipOffer;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getMotivationLetter() {
        return motivationLetter;
    }

    public void setMotivationLetter(String motivationLetter) {
        this.motivationLetter = motivationLetter;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
