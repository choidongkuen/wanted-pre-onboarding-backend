package com.example.pre_onboarding.service;

import com.example.pre_onboarding.constant.Area;
import com.example.pre_onboarding.domain.Company;
import com.example.pre_onboarding.domain.Recruitment;
import com.example.pre_onboarding.domain.User;
import com.example.pre_onboarding.domain.UserApplyRecruitment;
import com.example.pre_onboarding.dto.recruitment.CreateRecruitmentRequestDto;
import com.example.pre_onboarding.dto.recruitment.GetRecruitmentDetailResponseDto;
import com.example.pre_onboarding.dto.recruitment.GetRecruitmentsResponseDto;
import com.example.pre_onboarding.dto.recruitment.UpdateRecruitmentRequestDto;
import com.example.pre_onboarding.dto.user.UserAuthentication;
import com.example.pre_onboarding.exception.DuplicateUserApplyRecruitmentException;
import com.example.pre_onboarding.exception.recruitment.CompanyNotFoundException;
import com.example.pre_onboarding.exception.recruitment.RecruitmentNotFoundException;
import com.example.pre_onboarding.repository.CompanyRepository;
import com.example.pre_onboarding.repository.RecruitmentRepository;
import com.example.pre_onboarding.repository.UserApplyRecruitmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;
    private final UserApplyRecruitmentRepository userApplyRecruitmentRepository;

    /**
     * 채용 공고 등록
     **/
    @Transactional
    public Long createRecruitment(CreateRecruitmentRequestDto request) {
        Company company = getCompany(request.getCompanyId());
        Recruitment recruitment = request.toEntity(company);
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
        return this.getCompany(companyName).getRecruitments().stream()
                .sorted(Comparator.comparing(Recruitment::getUpdatedAt))
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

        // 1. id 해당하는 채용 공고(Recruitment) 찾고
        Recruitment recruitment = this.getRecruitment(id);
        // Long id 로 조회한 채용 공고의 회사가 올린 다른 모든 채용 공고
        return GetRecruitmentDetailResponseDto.toGetRecruitmentDetailResponseDto(
                recruitment, this.recruitmentRepository.findByCompany(recruitment.getCompany()));
    }

    /**
     * 지역 이름으로 채용 공고 조회
     */
    @Transactional(readOnly = true)
    public List<GetRecruitmentsResponseDto> getRecruitmentsByAreaName(Area name) {
        return this.companyRepository.findAllByArea(name).stream()
                .flatMap(company -> company.getRecruitments().stream()
                        .sorted(Comparator.comparing(Recruitment::getUpdatedAt))
                        .map(Recruitment::toGetRecruitmentsResponseDto))
                .collect(Collectors.toList());
    }

    /**
     * 사용자 채용 공고 지원
     */
    @Transactional
    public Long userApplyRecruitment(Long id) {
        Recruitment recruitment = this.getRecruitment(id);
        User user = ((UserAuthentication) SecurityContextHolder.getContext().getAuthentication()).getUser();

        // 유저가 동일한 채용 공고에 복수 지원한 경우 예외 발생
        if(this.userApplyRecruitmentRepository.countByRecruitmentAndUser(recruitment,user) >= 1) {
            throw new DuplicateUserApplyRecruitmentException("동일한 채용 공고에 복수 지원은 불가능 합니다.");
        }

        UserApplyRecruitment userApplyRecruitment = this.userApplyRecruitmentRepository.save(new UserApplyRecruitment(recruitment,user));
        return userApplyRecruitment.getId();
    }

    private Recruitment getRecruitment(Long id) {
        return this.recruitmentRepository.findById(id)
                .orElseThrow(() -> new RecruitmentNotFoundException("일치하는 채용 공고 정보가 존재하지 않습니다."));
    }

    private Company getCompany(Object cond) {
        if(cond instanceof Long) {
            return this.companyRepository.findById((Long)cond)
                    .orElseThrow(() -> new CompanyNotFoundException("일치하는 회사 정보가 존재하지 않습니다."));
        }else{
            return this.companyRepository.findByCompanyNameContainingIgnoreCase((String)cond)
                    .orElseThrow(() -> new CompanyNotFoundException("일치하는 회사 정보가 존재하지 않습니다."));
        }
    }
}
