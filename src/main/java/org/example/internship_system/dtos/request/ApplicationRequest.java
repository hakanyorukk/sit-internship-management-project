package org.example.internship_system.dtos.request;

public class ApplicationRequest {
    private Long studentId;
    private Long internshipOfferId;
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
