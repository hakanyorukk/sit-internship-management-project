package org.example.internship_system.repository;

import org.example.internship_system.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findByName(String name);
    Optional<Company> findByEmail(String email);
    Optional<Company> findByUserId(Long userId);

    boolean existsByName(String name);
    boolean existsByEmail(String email);
}
