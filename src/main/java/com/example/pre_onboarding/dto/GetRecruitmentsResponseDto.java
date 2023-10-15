package com.example.pre_onboarding.dto;

import com.example.pre_onboarding.constant.Area;
import com.example.pre_onboarding.constant.Nation;
import com.example.pre_onboarding.constant.Position;
import lombok.*;

/**
 * 채용 공고 조회 응답 DTO
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetRecruitmentsResponseDto {
    private Long id;
    private String companyName;
    private Nation nation;
    private Area area;
    private Position position;
    private Integer recruitmentBonus;
    private String recruitmentContent;
    private String skills;
}
