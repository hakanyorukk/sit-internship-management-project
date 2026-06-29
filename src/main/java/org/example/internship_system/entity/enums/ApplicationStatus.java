package org.example.internship_system.entity.enums;

/**
 * Lifecycle of a student's application to an internship offer.
 * A company reviews a PENDING application, moves it to UNDER_REVIEW,
 * then to APPROVED or REJECTED.
 */
public enum ApplicationStatus {
    PENDING,
    UNDER_REVIEW,
    APPROVED,
    REJECTED
}
