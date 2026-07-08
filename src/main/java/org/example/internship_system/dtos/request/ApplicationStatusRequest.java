package org.example.internship_system.dtos.request;

import org.example.internship_system.entity.enums.ApplicationStatus;

public class ApplicationStatusRequest {
    private ApplicationStatus status;
    private String comment;

    public ApplicationStatusRequest() {
    }

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
