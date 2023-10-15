package com.example.pre_onboarding.repository;

import com.example.pre_onboarding.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
