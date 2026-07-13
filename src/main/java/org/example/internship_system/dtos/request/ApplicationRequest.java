package org.example.internship_system.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ApplicationRequest {

    @NotNull(message = "Student id is required")
    private Long studentId;

    @NotNull(message = "Internship offer id is required")
    private Long internshipOfferId;

    @NotBlank(message = "Motivation letter is required")
    @Size(max = 2000, message = "Motivation letter must be at most 2000 characters")
    private String motivationLetter;

    public ApplicationRequest() {
    }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getInternshipOfferId() { return internshipOfferId; }
    public void setInternshipOfferId(Long internshipOfferId) { this.internshipOfferId = internshipOfferId; }

    public String getMotivationLetter() { return motivationLetter; }
    public void setMotivationLetter(String motivationLetter) { this.motivationLetter = motivationLetter; }
}
