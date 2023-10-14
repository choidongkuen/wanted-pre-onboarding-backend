package com.example.pre_onboarding.controller;

import com.example.pre_onboarding.dto.CreateRecruitmentRequestDto;
import com.example.pre_onboarding.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/* 채용 공고 CRUD 관련 Controller */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/recruitment")
@RestController
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    @PostMapping
    public ResponseEntity<Long> createRecruitment(
            @RequestBody @Valid CreateRecruitmentRequestDto request
    ) {
        return new ResponseEntity<>(
                this.recruitmentService.createRecruitment(request), HttpStatus.CREATED);
    }
}
