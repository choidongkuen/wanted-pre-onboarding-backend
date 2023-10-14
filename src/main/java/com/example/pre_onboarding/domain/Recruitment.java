package com.example.pre_onboarding.domain;

import com.example.pre_onboarding.constant.Position;
import com.example.pre_onboarding.dto.GetRecruitmentsResponseDto;
import com.example.pre_onboarding.dto.UpdateRecruitmentRequestDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "recruitments")
public class Recruitment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyId; // 회사 고유 id

    @Column(nullable = false)
    private String companyName; // 회사 이름

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Position position; // 채용 포지션

    private Integer recruitmentBonus; // 채용 보상금

    @Lob
    @Column(nullable = false)
    private String recruitmentContent; // 채용 내용

    private String skills; // 사용 기술(콤마 기준으로 여러개 설정 가능)

    public GetRecruitmentsResponseDto toGetRecruitmentsResponseDto() {
        return GetRecruitmentsResponseDto.builder()
                .id(id)
                .companyId(companyId)
                .companyName(companyName)
                .position(position)
                .recruitmentBonus(recruitmentBonus)
                .recruitmentContent(recruitmentContent)
                .skills(skills)
                .build();
    }

    public void updateRecruitment(UpdateRecruitmentRequestDto request) {
        this.companyName = request.getCompanyName();
        this.position = request.getPosition();
        this.recruitmentBonus = request.getRecruitmentBonus();
        this.recruitmentContent = request.getRecruitmentContent();
        this.skills = request.getSkills();
    }
}
