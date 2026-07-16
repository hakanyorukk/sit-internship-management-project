package org.example.internship_system.entity;

import jakarta.persistence.*;
import org.example.internship_system.entity.enums.CompanyRegistration;
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    private String website;
    private String contactEmail;
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanyRegistration registrationStatus = CompanyRegistration.PENDING;

    public Company() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getCity() {
        return city;
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
