package org.example.internship_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.internship_system.entity.enums.CompanyRegistration;

@Setter
@Getter
@NoArgsConstructor
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

}
