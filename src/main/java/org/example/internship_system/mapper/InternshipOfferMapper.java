package org.example.internship_system.mapper;

import org.example.internship_system.dtos.request.InternshipOfferRequest;
import org.example.internship_system.dtos.response.InternshipOfferResponse;
import org.example.internship_system.entity.InternshipOffer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InternshipOfferMapper {

    private final ModelMapper modelMapper;

    public InternshipOfferMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public InternshipOffer toEntity(InternshipOfferRequest request) {
        return modelMapper.map(request, InternshipOffer.class);
    }

    public InternshipOfferResponse toDto(InternshipOffer offer) {
        return modelMapper.map(offer, InternshipOfferResponse.class);
    }
}
