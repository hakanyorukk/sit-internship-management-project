package org.example.internship_system.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentProfileRequest {

    @NotBlank(message = "Faculty number is required")
    @Size(max = 50, message = "Faculty number must be at most 50 characters")
    private String facultyNumber;

    @Size(max = 255, message = "Specialty must be at most 255 characters")
    private String specialty;

    private Integer course;

    @Size(max = 1000, message = "Skills must be at most 1000 characters")
    private String skills;

    public StudentProfileRequest() {}

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
