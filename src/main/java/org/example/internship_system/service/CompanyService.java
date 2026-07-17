package org.example.internship_system.service;

import org.example.internship_system.dtos.request.CompanyRequest;
import org.example.internship_system.dtos.response.CompanyResponse;
import org.example.internship_system.entity.Company;
import org.example.internship_system.entity.User;
import org.example.internship_system.mapper.CompanyMapper;
import org.example.internship_system.repository.CompanyRepository;
import org.example.internship_system.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository,
                          CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.companyMapper = companyMapper;
    }

    @Transactional
    public CompanyResponse createCompany(CompanyRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (companyRepository.existsByName(request.getName())) {
            throw new RuntimeException("Company with name '" + request.getName() + "' already exists");
        }

        if (companyRepository.existsByContactEmail(request.getContactEmail())) {
            throw new RuntimeException("Company with email '" + request.getContactEmail() + "' already exists");
        }

        Company company = companyMapper.toEntity(request);
        company.setUser(user);

        Company savedCompany = companyRepository.save(company);
        return convertToResponse(savedCompany);
    }

    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public CompanyResponse getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        return convertToResponse(company);
    }

    public CompanyResponse getCompanyByName(String name) {
        Company company = companyRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Company not found with name: " + name));
        return convertToResponse(company);
    }

    public CompanyResponse getCompanyByContactEmail(String contactEmail) {
        Company company = companyRepository.findByContactEmail(contactEmail)
                .orElseThrow(() -> new RuntimeException("Company not found with email: " + contactEmail));
        return convertToResponse(company);
    }

    public CompanyResponse getCompanyByUserId(Long userId) {
        Company company = companyRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Company not found for user with id: " + userId));
        return convertToResponse(company);
    }

    @Transactional
    public CompanyResponse updateCompany(Long id, CompanyRequest request) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));

        if (!company.getName().equals(request.getName()) &&
                companyRepository.existsByName(request.getName())) {
            throw new RuntimeException("Company with name " + request.getName() + "already exists");
        }
        if (!company.getContactEmail().equals(request.getContactEmail()) &&
                companyRepository.existsByContactEmail(request.getContactEmail())) {
            throw new RuntimeException("Company with email " + request.getContactEmail() + "already exists");
        }

        company.setName(request.getName());
        company.setDescription(request.getDescription());
        company.setCity(request.getCity());
        company.setContactEmail(request.getContactEmail());

        Company updatedCompany = companyRepository.save(company);
        return convertToResponse(updatedCompany);
    }

    @Transactional
    public void deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Company not found with id: " + id);
        }
        companyRepository.deleteById(id);
    }

    private CompanyResponse convertToResponse(Company company) {
        return companyMapper.toDto(company);
    }
}