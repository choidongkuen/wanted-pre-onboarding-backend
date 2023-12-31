package com.example.pre_onboarding.dto.recruitment;

import com.example.pre_onboarding.constant.Area;
import com.example.pre_onboarding.constant.Nation;
import com.example.pre_onboarding.constant.Position;
import com.example.pre_onboarding.domain.Recruitment;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetRecruitmentDetailResponseDto {
    private Long id;
    private String companyName;
    private Nation nation;
    private Area area;
    private Position position;
    private Integer recruitmentBonus;
    private String recruitmentContent;
    private String skills;
    // 해당 회사가 올린 다른 채용 공고 id
    private List<Long> otherRecruitments;

    public static GetRecruitmentDetailResponseDto toGetRecruitmentDetailResponseDto(
            Recruitment recruitment,
            List<Recruitment> otherRecruitments) {

        otherRecruitments.remove(recruitment); // 자기 자신 삭제
        return GetRecruitmentDetailResponseDto.builder()
                .id(recruitment.getId())
                .companyName(recruitment.getCompany().getCompanyName())
                .nation(recruitment.getCompany().getNation())
                .area(recruitment.getCompany().getArea())
                .position(recruitment.getPosition())
                .recruitmentBonus(recruitment.getRecruitmentBonus())
                .recruitmentContent(recruitment.getRecruitmentContent())
                .skills(recruitment.getSkills())
                .otherRecruitments(otherRecruitments.stream()
                        .map(Recruitment::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
