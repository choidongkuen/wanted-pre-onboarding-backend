package com.example.pre_onboarding.controller;

import com.example.pre_onboarding.config.JwtProperties;
import com.example.pre_onboarding.dto.user.TokenResponseDto;
import com.example.pre_onboarding.dto.user.UserJoinRequestDto;
import com.example.pre_onboarding.dto.user.UserLoginRequestDto;
import com.example.pre_onboarding.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;
    private final JwtProperties jwtProperties;
    /**
     * 유저 회원 가입
     */
    @PostMapping("/join")
    public ResponseEntity<Long> joinUser(
            @RequestBody @Valid UserJoinRequestDto request
    ) {
        return new ResponseEntity<>(this.userService.joinUser(request), HttpStatus.CREATED);
    }

    /**
     * 유저 로그인
     */

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(
            @RequestBody @Valid UserLoginRequestDto request
    ) {
        return new ResponseEntity<>(this.userService.loginUser(request), HttpStatus.OK);
    }
}
