package org.example.internship_system.controller;

import org.example.internship_system.dtos.request.ApplicationRequest;
import org.example.internship_system.dtos.request.ApplicationStatusRequest;
import org.example.internship_system.dtos.response.ApplicationResponse;
import org.example.internship_system.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ApplicationResponse create(@RequestBody ApplicationRequest request) {
        return applicationService.create(request);
    }

    @GetMapping
    public List<ApplicationResponse> getAll() {
        return applicationService.getAll();
    }

    @GetMapping("/my")
    public List<ApplicationResponse> getMy(@RequestParam Long studentId) {
        return applicationService.getByStudent(studentId);
    }

    @GetMapping("/{id}")
    public ApplicationResponse getById(@PathVariable Long id) {
        return applicationService.getById(id);
    }

    @PatchMapping("/{id}/status")
    public ApplicationResponse updateStatus(@PathVariable Long id,
                                            @RequestBody ApplicationStatusRequest request) {
        return applicationService.updateStatus(id, request);
    }
}
