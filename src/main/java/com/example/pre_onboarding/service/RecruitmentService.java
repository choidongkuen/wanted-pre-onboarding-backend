package com.example.pre_onboarding.service;

import com.example.pre_onboarding.domain.Recruitment;
import com.example.pre_onboarding.dto.CreateRecruitmentRequestDto;
import com.example.pre_onboarding.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;

    @Transactional
    public Long createRecruitment(CreateRecruitmentRequestDto request) {
        Recruitment recruitment = request.toEntity();
        return this.recruitmentRepository.save(recruitment).getId();
    }
}
