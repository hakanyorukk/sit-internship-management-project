package org.example.internship_system.mapper;

import org.example.internship_system.dtos.request.ApplicationRequest;
import org.example.internship_system.dtos.response.ApplicationResponse;
import org.example.internship_system.entity.Application;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    private final ModelMapper modelMapper;

    public ApplicationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Application toEntity(ApplicationRequest request) {
        return modelMapper.map(request, Application.class);
    }

    public ApplicationResponse toDto(Application application) {
        return modelMapper.map(application, ApplicationResponse.class);
    }
}
