package org.example.internship_system.controller;

import org.example.internship_system.entity.enums.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/enums")
public class EnumController {

    public record EnumOption(String value, String label) {}

    @GetMapping("/work-types")
    public List<EnumOption> workTypes() {
        return toOptions(WorkType.values());
    }

    @GetMapping("/roles")
    public List<EnumOption> roles() {
        return toOptions(Role.values());
    }

    @GetMapping("/application-statuses")
    public List<EnumOption> applicationStatuses() {
        return toOptions(ApplicationStatus.values());
    }

    @GetMapping("/company-registration")
    public List<EnumOption> companyRegistration() {
        return toOptions(CompanyRegistration.values());
    }

    @GetMapping("/offer-statuses")
    public List<EnumOption> offerStatuses() {
        return toOptions(OfferStatus.values());
    }

    // convert enum values to readable strings = "ON_SITE" -> "On site"
    private <E extends Enum<E>> List<EnumOption> toOptions(E[] values) {
        return Arrays.stream(values)
                .map(e -> new EnumOption(e.name(), toLabel(e.name())  ))
                .toList();
    }

    // "ON_SITE" -> "On site", "UNDER_REVIEW" -> "Under review"
    private String toLabel(String name) {
        String lower = name.replace('_', ' ').toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
}