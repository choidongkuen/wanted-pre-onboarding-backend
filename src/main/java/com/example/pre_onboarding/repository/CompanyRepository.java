package com.example.pre_onboarding.repository;

import com.example.pre_onboarding.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findByCompanyNameContainingIgnoreCase(String companyName);
}
