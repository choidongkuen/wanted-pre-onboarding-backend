package com.example.pre_onboarding.service;

import com.example.pre_onboarding.domain.User;
import com.example.pre_onboarding.dto.user.TokenResponseDto;
import com.example.pre_onboarding.dto.user.UserJoinRequestDto;
import com.example.pre_onboarding.dto.user.UserLoginRequestDto;
import com.example.pre_onboarding.exception.user.PasswordNotMatchException;
import com.example.pre_onboarding.exception.user.UserNotFoundException;
import com.example.pre_onboarding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    @Transactional
    public Long joinUser(UserJoinRequestDto request) {
        return this.userRepository.save(
                request.toEntity(makeUserId(),this.bCryptPasswordEncoder.encode(request.getPassword()))).getId();
    }

    @Transactional
    public TokenResponseDto loginUser(UserLoginRequestDto request) {
        User user = this.userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("일치하는 회원이 존재하지 않습니다."));

        if(!this.bCryptPasswordEncoder.matches(request.getPassword(),user.getPassword())) {
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }
        return this.jwtService.getServiceToken(user);
    }

    private static String makeUserId() {
        return UUID.randomUUID().toString();
    }

}
