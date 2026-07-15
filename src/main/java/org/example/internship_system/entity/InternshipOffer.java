package org.example.internship_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.internship_system.entity.enums.OfferStatus;
import org.example.internship_system.entity.enums.WorkType;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "internship_offers")
public class InternshipOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 4000)
    private String description;

    @Column(length = 2000)
    private String requiredSkills;

    private String location;

    @Enumerated(EnumType.STRING)
    private WorkType type;

    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    private OfferStatus status = OfferStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;


    public InternshipOffer() {}

}
