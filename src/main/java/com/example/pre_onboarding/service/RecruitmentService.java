package com.example.pre_onboarding.service;

import com.example.pre_onboarding.domain.Recruitment;
import com.example.pre_onboarding.dto.CreateRecruitmentRequestDto;
import com.example.pre_onboarding.dto.GetRecruitmentDetailResponseDto;
import com.example.pre_onboarding.dto.GetRecruitmentsResponseDto;
import com.example.pre_onboarding.dto.UpdateRecruitmentRequestDto;
import com.example.pre_onboarding.exception.NotFoundRecruitmentException;
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

    /**
     * 채용 공고 등록
     **/
    @Transactional
    public Long createRecruitment(CreateRecruitmentRequestDto request) {
        Recruitment recruitment = request.toEntity();
        return this.recruitmentRepository.save(recruitment).getId();
    }

    /**
     * 모든 채용 공고 조회
     **/
    @Transactional(readOnly = true)
    public List<GetRecruitmentsResponseDto> getRecruitments() {
        // UpdatedAt 기준 내림차순(수정일자 기준 최신 순)
        return this.recruitmentRepository.findByOrderByUpdatedAtDesc().stream()
                .map(Recruitment::toGetRecruitmentsResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * 채용 공고 수정
     **/
    @Transactional
    public void updateRecruitment(Long id, UpdateRecruitmentRequestDto request) {
        this.getRecruitment(id).updateRecruitment(request);
    }


    /**
     * 채용 공고 삭제
     */
    @Transactional
    public void deleteRecruitment(Long id) {
        this.recruitmentRepository.delete(getRecruitment(id));
    }

    /**
     * 회사 이름으로 채용 공고 조회
     */
    @Transactional(readOnly = true)
    public List<GetRecruitmentsResponseDto> getRecruitmentsByCompanyName(String companyName) {
        return this.recruitmentRepository
                // companyName 이 포함된 모든 채용공고 조회
                .findByCompanyNameContainingIgnoreCaseOrderByUpdatedAtDesc(companyName).stream()
                .map(Recruitment::toGetRecruitmentsResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * 기술 이름으로 채용 공고 조회
     */
    @Transactional(readOnly = true)
    public List<GetRecruitmentsResponseDto> getRecruitmentsBySkillName(String skillName) {
        return this.recruitmentRepository
                // skillName 이 포함된 모든 채용공고 조회
                .findBySkillsContainingIgnoreCaseOrderByUpdatedAtDesc(skillName).stream()
                .map(Recruitment::toGetRecruitmentsResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * 채용 공고 상세 조회
     */
    @Transactional(readOnly = true)
    public GetRecruitmentDetailResponseDto getRecruitmentDetail(Long id) {
        Recruitment recruitment = this.getRecruitment(id);
        // Long id 로 조회한 채용 공고의 회사가 올린 다른 모든 채용 공고
        return GetRecruitmentDetailResponseDto.toGetRecruitmentDetailResponseDto(
                recruitment, this.recruitmentRepository.findByCompanyId(recruitment.getCompanyId()));
    }
    private Recruitment getRecruitment(Long id) {
        return this.recruitmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundRecruitmentException("일치하는 채용 공고 정보가 존재하지 않습니다."));
    }
}