package org.example.internship_system.dtos.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.internship_system.entity.enums.WorkType;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class InternshipOfferRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be at most 255 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 4000, message = "Description must be at most 4000 characters")
    private String description;

    @NotBlank(message = "Required skills are required")
    @Size(max = 2000, message = "Required skills must be at most 2000 characters")
    private String requiredSkills;

    @NotBlank(message = "Location is required")
    @Size(max = 255, message = "Location must be at most 255 characters")
    private String location;

    @NotNull(message = "Work type is required")
    private WorkType type;

    @NotNull(message = "Deadline is required")
    @Future(message = "Deadline must be a future date")
    private LocalDate deadline;

    @NotNull(message = "Company id is required")
    private Long companyId;

}
