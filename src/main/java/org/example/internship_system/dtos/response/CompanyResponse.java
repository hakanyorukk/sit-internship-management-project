package org.example.internship_system.dtos.response;

public class CompanyResponse {
    private Long id;
    private String name;
    private String description;
    private String website;
    private String city;
    private String contactEmail;

    public CompanyResponse(){

    }

    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {

        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCity() {

        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public Long getId(){return id;}
    public void setId(){
        this.id = id;
    }

}
