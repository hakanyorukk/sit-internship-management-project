package org.example.internship_system.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.internship_system.entity.enums.ApplicationStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
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

}
