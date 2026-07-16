package org.example.internship_system.mapper;

import org.example.internship_system.dtos.response.StudentProfileResponse;
import org.example.internship_system.entity.StudentProfile;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentProfileMapper {

    private final ModelMapper modelMapper;

    public StudentProfileMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StudentProfileResponse toDto(StudentProfile profile) {
        return modelMapper.map(profile, StudentProfileResponse.class);
    }
}
