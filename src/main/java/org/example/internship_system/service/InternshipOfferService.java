package org.example.internship_system.service;

import org.example.internship_system.dtos.request.InternshipOfferRequest;
import org.example.internship_system.dtos.response.InternshipOfferResponse;
import org.example.internship_system.entity.Company;
import org.example.internship_system.entity.InternshipOffer;
import org.example.internship_system.entity.enums.OfferStatus;
import org.example.internship_system.exception.ResourceNotFoundException;
import org.example.internship_system.mapper.InternshipOfferMapper;
import org.example.internship_system.repository.CompanyRepository;
import org.example.internship_system.repository.InternshipOfferRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InternshipOfferService {

    private final InternshipOfferRepository internshipOfferRepository;
    private final CompanyRepository companyRepository;
    private final InternshipOfferMapper internshipOfferMapper;

    public InternshipOfferService(InternshipOfferRepository internshipOfferRepository,
                                  CompanyRepository companyRepository,
                                  InternshipOfferMapper internshipOfferMapper) {
        this.internshipOfferRepository = internshipOfferRepository;
        this.companyRepository = companyRepository;
        this.internshipOfferMapper = internshipOfferMapper;
    }

    public InternshipOfferResponse create(InternshipOfferRequest request) {
        // A company can only post offers under its OWN company, derived from the
        // logged-in user (the JWT) — not from a companyId in the request body.
        // Otherwise one company could create offers under another company's id.
        Company company = currentCompany();

        InternshipOffer offer = internshipOfferMapper.toEntity(request);
        offer.setStatus(OfferStatus.ACTIVE);
        offer.setCompany(company);

        InternshipOffer saved = internshipOfferRepository.save(offer);
        return toResponse(saved);
    }

    public List<InternshipOfferResponse> getAll() {
        List<InternshipOfferResponse> result = new ArrayList<>();
        for (InternshipOffer offer : internshipOfferRepository.findAll()) {
            result.add(toResponse(offer));
        }
        return result;
    }

    public InternshipOfferResponse getById(Long id) {
        InternshipOffer offer = internshipOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Internship offer not found: " + id));
        return toResponse(offer);
    }

    public List<InternshipOfferResponse> getByCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Company not found: " + companyId));
        List<InternshipOfferResponse> result = new ArrayList<>();
        for (InternshipOffer offer : internshipOfferRepository.findByCompany(company)) {
            result.add(toResponse(offer));
        }
        return result;
    }

    private Company currentCompany() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return companyRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("No company for user: " + email));
    }

    public List<InternshipOfferResponse> getMyOffers() {
        Company company = currentCompany();
        List<InternshipOfferResponse> result = new ArrayList<>();
        for (InternshipOffer offer : internshipOfferRepository.findByCompany(company)) {
            result.add(toResponse(offer));
        }
        return result;
    }

    private void checkOwnership(InternshipOffer offer)  {
        Company company = currentCompany();
        if (!offer.getCompany().getId().equals(company.getId())) {
            throw new AccessDeniedException("You can only manage your own offers");
        }
    }
    public void delete(Long id)  {
        InternshipOffer offer = internshipOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship offer not found: " + id));
        checkOwnership(offer);
        internshipOfferRepository.delete(offer);
    }

    public InternshipOfferResponse update(Long id, InternshipOfferRequest request)  {
        InternshipOffer offer = internshipOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship offer not found: " + id));
        checkOwnership(offer);

        // Only the editable fields are overwritten. company and status are left as
        // they are, so an update cannot break the company relation or reset the status.
        offer.setTitle(request.getTitle());
        offer.setDescription(request.getDescription());
        offer.setRequiredSkills(request.getRequiredSkills());
        offer.setLocation(request.getLocation());
        offer.setType(request.getType());
        offer.setDeadline(request.getDeadline());

        InternshipOffer saved = internshipOfferRepository.save(offer);
        return toResponse(saved);
    }

    private InternshipOfferResponse toResponse(InternshipOffer offer) {
        return internshipOfferMapper.toDto(offer);
    }
}
