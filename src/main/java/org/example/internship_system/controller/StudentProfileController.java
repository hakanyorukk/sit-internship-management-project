package org.example.internship_system.controller;

import jakarta.validation.Valid;
import org.example.internship_system.dtos.request.StudentProfileRequest;
import org.example.internship_system.dtos.response.StudentProfileResponse;
import org.example.internship_system.service.StudentProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @GetMapping("/me")
    public ResponseEntity<StudentProfileResponse> getMyProfile() {
        return ResponseEntity.ok(studentProfileService.getMyProfile());
    }

    @PutMapping("/me")
    public ResponseEntity<StudentProfileResponse> updateMyProfile(
            @Valid @RequestBody StudentProfileRequest request) {
        return ResponseEntity.ok(studentProfileService.updateMyProfile(request));
    }
}
