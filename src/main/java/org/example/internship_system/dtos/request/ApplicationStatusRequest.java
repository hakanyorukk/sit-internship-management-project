package org.example.internship_system.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.internship_system.entity.enums.ApplicationStatus;

@Setter
@Getter
@NoArgsConstructor
public class ApplicationStatusRequest {

    @NotNull(message = "Status is required")
    private ApplicationStatus status;

    @Size(max = 1000, message = "Comment must be at most 1000 characters")
    private String comment;

}
