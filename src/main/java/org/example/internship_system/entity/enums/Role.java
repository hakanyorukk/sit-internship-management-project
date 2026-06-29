package org.example.internship_system.entity.enums;

/**
 * The kind of account a {@code User} has. Drives role-based access in Spring Security
 * and decides which profile (StudentProfile / Company) is attached to the user.
 */
public enum Role {
    STUDENT,
    COMPANY,
    ADMIN
}
