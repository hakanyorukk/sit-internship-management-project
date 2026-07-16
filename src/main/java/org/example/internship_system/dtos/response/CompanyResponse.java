package org.example.internship_system.dtos.response;
import org.example.internship_system.entity.enums.CompanyRegistration;
public class CompanyResponse {
    private Long id;
    private String name;
    private String description;
    private String website;
    private String city;
    private String contactEmail;
    private CompanyRegistration registrationStatus;

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
  
    public Long getId(){
      return id;
    }
  
    public void setId(Long id){
        this.id = id;
    }
  
    public void setCity(String city) {
        this.city = city;
    }

    public CompanyRegistration getRegistrationStatus() {
        return registrationStatus;
    }
  
    public void setRegistrationStatus(CompanyRegistration registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
