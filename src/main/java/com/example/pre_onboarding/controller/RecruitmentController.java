package com.example.pre_onboarding.controller;

import com.example.pre_onboarding.dto.CreateRecruitmentRequestDto;
import com.example.pre_onboarding.dto.GetRecruitmentsResponseDto;
import com.example.pre_onboarding.dto.UpdateRecruitmentRequestDto;
import com.example.pre_onboarding.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/* 채용 공고 CRUD 관련 Controller */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/recruitment")
@RestController
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    /**
     * 채용 공고 등록
     **/
    @PostMapping
    public ResponseEntity<Long> createRecruitment(
            @RequestBody @Valid CreateRecruitmentRequestDto request
    ) {
        return new ResponseEntity<>(
                this.recruitmentService.createRecruitment(request), HttpStatus.CREATED);
    }

    /**
     * 모든 채용 공고 조회
     **/
    @GetMapping
    public ResponseEntity<List<GetRecruitmentsResponseDto>> getRecruitments() {
        return new ResponseEntity<>(
                this.recruitmentService.getRecruitments(),HttpStatus.OK);
    }

    /**
     * 채용 공고 수정
     **/
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateRecruitment(
            @PathVariable Long id,
            @RequestBody UpdateRecruitmentRequestDto request
    ) {
        this.recruitmentService.updateRecruitment(id,request);
        return ResponseEntity.ok().build();
    }

    /**
     * 채용 공고 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecruitment(
            @PathVariable Long id
    ) {
        this.recruitmentService.deleteRecruitment(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 회사 이름으로 채용 공고 조회
     */
    @GetMapping("/filter/company")
    public ResponseEntity<List<GetRecruitmentsResponseDto>> getRecruitmentsByCompanyName(
            @RequestParam String name
    ) {
       return new ResponseEntity<>(
               this.recruitmentService.getRecruitmentsByCompanyName(name),HttpStatus.OK);
    }

    /**
     * 기술 이름으로 채용 공고 조회
     */
    @GetMapping("/filter/skill")
    public ResponseEntity<List<GetRecruitmentsResponseDto>> getRecruitmentsBySkillName(
            @RequestParam String name
    ) {
        return new ResponseEntity<>(
                this.recruitmentService.getRecruitmentsBySkillName(name), HttpStatus.OK);
    }
}
