package com.example.pre_onboarding.repository;

import com.example.pre_onboarding.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserId(String userId);

    Optional<User> findByEmail(String email);
}
