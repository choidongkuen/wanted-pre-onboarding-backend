package com.example.pre_onboarding.repository;

import com.example.pre_onboarding.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment,Long> {
    List<Recruitment> findByOrderByUpdatedAtDesc();
}
