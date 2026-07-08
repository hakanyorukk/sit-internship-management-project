package org.example.internship_system.dtos.response;

import org.example.internship_system.entity.enums.ApplicationStatus;

import java.time.LocalDateTime;

public class ApplicationResponse {
    private Long id;
    private Long studentId;
    private String studentFacultyNumber;
    private Long internshipOfferId;
    private String internshipOfferTitle;
    private LocalDateTime applicationDate;
    private String motivationLetter;
    private ApplicationStatus status;
    private String comment;

    public ApplicationResponse() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentFacultyNumber() { return studentFacultyNumber; }
    public void setStudentFacultyNumber(String studentFacultyNumber) { this.studentFacultyNumber = studentFacultyNumber; }

    public Long getInternshipOfferId() { return internshipOfferId; }
    public void setInternshipOfferId(Long internshipOfferId) { this.internshipOfferId = internshipOfferId; }

    public String getInternshipOfferTitle() { return internshipOfferTitle; }
    public void setInternshipOfferTitle(String internshipOfferTitle) { this.internshipOfferTitle = internshipOfferTitle; }

    public LocalDateTime getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDateTime applicationDate) { this.applicationDate = applicationDate; }

    public String getMotivationLetter() { return motivationLetter; }
    public void setMotivationLetter(String motivationLetter) { this.motivationLetter = motivationLetter; }

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
