package org.example.internship_system.service;

import org.example.internship_system.dtos.request.LoginRequest;
import org.example.internship_system.dtos.request.RegisterRequest;
import org.example.internship_system.entity.StudentProfile;
import org.example.internship_system.entity.User;
import org.example.internship_system.entity.enums.Role;
import org.example.internship_system.repository.StudentProfileRepository;
import org.example.internship_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentProfileRepository studentProfileRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       StudentProfileRepository studentProfileRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.studentProfileRepository = studentProfileRepository;
    }

    @Transactional
    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered: " + request.getEmail());
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // BCrypt hash here
        user.setRole(request.getRole());
        user.setCreatedAt(LocalDateTime.now());

        User saved = userRepository.save(user);

        // Every STUDENT gets a profile right away, so they can apply to offers
        // without a separate "create profile" step. Fields the registration form
        // did not send stay null and can be filled later via PUT /api/students/me.
        if (saved.getRole() == Role.STUDENT) {
            StudentProfile profile = new StudentProfile();
            profile.setUser(saved);
            profile.setFacultyNumber(request.getFacultyNumber());
            profile.setSpecialty(request.getSpecialty());
            profile.setCourse(request.getCourse());
            profile.setSkills(request.getSkills());
            studentProfileRepository.save(profile);
        }

        return saved;
    }

    public String verify(LoginRequest request) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));


        if(authentication.isAuthenticated()) {
            User user = userRepository.findByEmail(request.getEmail());
            return jwtService.generateToken(request.getEmail(), user.getRole().name());
        }
        return "fail";
    }
}