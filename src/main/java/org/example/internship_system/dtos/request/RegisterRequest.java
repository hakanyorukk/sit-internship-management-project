package org.example.internship_system.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.internship_system.entity.enums.Role;

@Setter
@Getter
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;

       @Size(max = 50, message = "Faculty number must be at most 50 characters")
      private String facultyNumber;

      @Size(max = 255, message = "Specialty must be at most 255 characters")
      private String specialty;

      private Integer course;

      @Size(max = 1000, message = "Skills must be at most 1000 characters")
      private String skills;


}
