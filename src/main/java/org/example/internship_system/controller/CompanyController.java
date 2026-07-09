package org.example.internship_system.controller;

import org.example.internship_system.dtos.request.CompanyRequest;
import org.example.internship_system.dtos.response.CompanyResponse;
import org.example.internship_system.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;


    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyRequest request) {
        CompanyResponse response = companyService.createCompany(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        List<CompanyResponse> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) {
        CompanyResponse response = companyService.getCompanyById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/name")
    public ResponseEntity<CompanyResponse> getCompanyByName(@RequestParam String name) {
        CompanyResponse response = companyService.getCompanyByName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/email")
    public ResponseEntity<CompanyResponse> getCompanyByContactEmail(@RequestParam String contactEmail) {
        CompanyResponse response = companyService.getCompanyByContactEmail(contactEmail);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/user")
    public ResponseEntity<CompanyResponse> getCompanyByUserId(@RequestParam Long userId) {
        CompanyResponse response = companyService.getCompanyByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable Long id,
            @RequestBody CompanyRequest request) {
        CompanyResponse response = companyService.updateCompany(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}