package com.example.pre_onboarding.controller;

import com.example.pre_onboarding.dto.user.UserJoinRequestDto;
import com.example.pre_onboarding.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    /**
     * 유저 회원 가입
     */
    @PostMapping("/join")
    public ResponseEntity<Long> joinUser(
            @RequestBody UserJoinRequestDto request
    ) {
        return new ResponseEntity<>(this.userService.joinUser(request), HttpStatus.CREATED);
    }
}
