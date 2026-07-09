package org.example.internship_system.mapper;

import org.example.internship_system.dtos.request.CompanyRequest;
import org.example.internship_system.dtos.response.CompanyResponse;
import org.example.internship_system.entity.Company;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    private final ModelMapper modelMapper;

    public CompanyMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Company toEntity(CompanyRequest request) {
        return modelMapper.map(request, Company.class);
    }

    public CompanyResponse toDto(Company company) {
        return modelMapper.map(company, CompanyResponse.class);
    }
}
