package org.example.internship_system.service;

import org.example.internship_system.dtos.request.StudentProfileRequest;
import org.example.internship_system.dtos.response.StudentProfileResponse;
import org.example.internship_system.entity.StudentProfile;
import org.example.internship_system.exception.ResourceNotFoundException;
import org.example.internship_system.mapper.StudentProfileMapper;
import org.example.internship_system.repository.StudentProfileRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;
    private final StudentProfileMapper studentProfileMapper;

    public StudentProfileService(StudentProfileRepository studentProfileRepository,
                                 StudentProfileMapper studentProfileMapper) {
        this.studentProfileRepository = studentProfileRepository;
        this.studentProfileMapper = studentProfileMapper;
    }

    // The profile of whoever is logged in. Profiles are auto-created at
    // registration, so a missing profile means the account is not a student
    // (or predates the auto-create change).
    private StudentProfile currentProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return studentProfileRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No student profile for user: " + email));
    }

    public StudentProfileResponse getMyProfile() {
        return studentProfileMapper.toDto(currentProfile());
    }

    @Transactional
    public StudentProfileResponse updateMyProfile(StudentProfileRequest request) {
        StudentProfile profile = currentProfile();

        profile.setFacultyNumber(request.getFacultyNumber());
        profile.setSpecialty(request.getSpecialty());
        profile.setCourse(request.getCourse());
        profile.setSkills(request.getSkills());

        StudentProfile saved = studentProfileRepository.save(profile);
        return studentProfileMapper.toDto(saved);
    }
}
