package org.example.internship_system.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ApplicationRequest {

    @NotNull(message = "Student id is required")
    private Long studentId;

    @NotNull(message = "Internship offer id is required")
    private Long internshipOfferId;

    @NotBlank(message = "Motivation letter is required")
    @Size(max = 2000, message = "Motivation letter must be at most 2000 characters")
    private String motivationLetter;

}
