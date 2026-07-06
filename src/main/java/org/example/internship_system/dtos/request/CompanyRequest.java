package org.example.internship_system.dtos.request;

public class CompanyRequest {
    private String name;
    private String description;
    private String city;
    private String contactEmail;

    public CompanyRequest(){

    }

    public String getName(){
        return name;
    }
    public void setName(String name){
       this.name = name;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

}
