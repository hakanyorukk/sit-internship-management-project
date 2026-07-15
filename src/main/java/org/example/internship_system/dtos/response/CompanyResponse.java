package org.example.internship_system.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyResponse {
    private Long id;
    private String name;
    private String description;
    private String website;
    private String city;
    private String contactEmail;

    public CompanyResponse(){

    }

}
