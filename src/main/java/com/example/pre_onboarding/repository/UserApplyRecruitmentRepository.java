package com.example.pre_onboarding.repository;

import com.example.pre_onboarding.domain.Recruitment;
import com.example.pre_onboarding.domain.User;
import com.example.pre_onboarding.domain.UserApplyRecruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserApplyRecruitmentRepository extends JpaRepository<UserApplyRecruitment,Long> {
    long countByRecruitmentAndUser(Recruitment recruitment, User user);
}
