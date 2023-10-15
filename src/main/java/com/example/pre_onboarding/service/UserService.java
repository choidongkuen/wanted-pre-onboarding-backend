package com.example.pre_onboarding.service;

import com.example.pre_onboarding.dto.user.UserJoinRequestDto;
import com.example.pre_onboarding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Transactional
    public Long joinUser(UserJoinRequestDto request) {
        return this.userRepository.save(
                request.toEntity(makeUserId(),this.bCryptPasswordEncoder.encode(request.getPassword()))).getId();
    }

    private static String makeUserId() {
        return UUID.randomUUID().toString();
    }
}
