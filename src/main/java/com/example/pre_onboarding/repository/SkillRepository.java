package com.example.pre_onboarding.repository;

import com.example.pre_onboarding.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill,Long> {
}
