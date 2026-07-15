package org.example.internship_system.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be at most 255 characters")
    private String name;

    @Size(max = 2000, message = "Description must be at most 2000 characters")
    private String description;

    @NotNull(message = "User id is required")
    private Long id;

    @NotBlank(message = "Website is required")
    @Size(min = 3, max = 255, message = "Website must be between 3 and 255 characters")
    @Pattern(
            regexp = "^(www\\.)?[a-zA-Z0-9\\-]+\\.([a-zA-Z]{2,})(\\.[a-zA-Z]{2,})?$",
            message = "Website must be a valid domain name"
    )
    private String website;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Contact email is required")
    @Email(message = "Contact email must be a valid email address")
    private String contactEmail;

    public CompanyRequest(){

    }

}
