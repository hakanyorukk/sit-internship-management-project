package org.example.internship_system.service;

import org.example.internship_system.dtos.request.ApplicationRequest;
import org.example.internship_system.dtos.request.ApplicationStatusRequest;
import org.example.internship_system.dtos.response.ApplicationResponse;
import org.example.internship_system.entity.Application;
import org.example.internship_system.entity.InternshipOffer;
import org.example.internship_system.entity.StudentProfile;
import org.example.internship_system.entity.enums.ApplicationStatus;
import org.example.internship_system.entity.enums.OfferStatus;
import org.example.internship_system.exception.ResourceNotFoundException;
import org.example.internship_system.repository.ApplicationRepository;
import org.example.internship_system.repository.InternshipOfferRepository;
import org.example.internship_system.repository.StudentProfileRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final InternshipOfferRepository internshipOfferRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              StudentProfileRepository studentProfileRepository,
                              InternshipOfferRepository internshipOfferRepository) {
        this.applicationRepository = applicationRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.internshipOfferRepository = internshipOfferRepository;
    }

    public ApplicationResponse create(ApplicationRequest request) {
        StudentProfile student = studentProfileRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student profile not found: " + request.getStudentId()));

        InternshipOffer offer = internshipOfferRepository.findById(request.getInternshipOfferId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Internship offer not found: " + request.getInternshipOfferId()));

        if (offer.getStatus() != OfferStatus.ACTIVE) {
            throw new IllegalStateException("Cannot apply to a closed offer: " + offer.getId());
        }

        if (applicationRepository.existsByStudentAndInternshipOffer(student, offer)) {
            throw new IllegalStateException(
                    "Student " + student.getId() + " has already applied to offer " + offer.getId());
        }

        Application application = new Application();
        application.setStudent(student);
        application.setInternshipOffer(offer);
        application.setMotivationLetter(request.getMotivationLetter());
        application.setApplicationDate(LocalDateTime.now());
        application.setStatus(ApplicationStatus.PENDING);

        Application saved = applicationRepository.save(application);
        return toResponse(saved);
    }

    public List<ApplicationResponse> getAll() {
        List<ApplicationResponse> result = new ArrayList<>();
        for (Application application : applicationRepository.findAll()) {
            result.add(toResponse(application));
        }
        return result;
    }

    public ApplicationResponse getById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found: " + id));
        return toResponse(application);
    }

    public List<ApplicationResponse> getByStudent(Long studentId) {
        StudentProfile student = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student profile not found: " + studentId));
        List<ApplicationResponse> result = new ArrayList<>();
        for (Application application : applicationRepository.findByStudent(student)) {
            result.add(toResponse(application));
        }
        return result;
    }

    public List<ApplicationResponse> getByOffer(Long offerId) {
        InternshipOffer offer = internshipOfferRepository.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Internship offer not found: " + offerId));
        List<ApplicationResponse> result = new ArrayList<>();
        for (Application application : applicationRepository.findByInternshipOffer(offer)) {
            result.add(toResponse(application));
        }
        return result;
    }

    public ApplicationResponse updateStatus(Long id, ApplicationStatusRequest request) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found: " + id));

        application.setStatus(request.getStatus());
        if (request.getComment() != null) {
            application.setComment(request.getComment());
        }

        Application saved = applicationRepository.save(application);
        return toResponse(saved);
    }

    private ApplicationResponse toResponse(Application application) {
        ApplicationResponse response = new ApplicationResponse();
        response.setId(application.getId());
        response.setApplicationDate(application.getApplicationDate());
        response.setMotivationLetter(application.getMotivationLetter());
        response.setStatus(application.getStatus());
        response.setComment(application.getComment());
        if (application.getStudent() != null) {
            response.setStudentId(application.getStudent().getId());
            response.setStudentFacultyNumber(application.getStudent().getFacultyNumber());
        }
        if (application.getInternshipOffer() != null) {
            response.setInternshipOfferId(application.getInternshipOffer().getId());
            response.setInternshipOfferTitle(application.getInternshipOffer().getTitle());
        }
        return response;
    }
}
