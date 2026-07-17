package org.example.internship_system.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.example.internship_system.entity.enums.OfferStatus;
import org.example.internship_system.entity.enums.WorkType;

import java.time.LocalDate;

@Setter
@Getter
public class InternshipOfferResponse {
    private Long id;
    private String title;
    private String description;
    private String requiredSkills;
    private String location;
    private WorkType type;
    private LocalDate deadline;
    private OfferStatus status;
    private Long companyId;
    private String companyName;

    public InternshipOfferResponse() {
    }

}