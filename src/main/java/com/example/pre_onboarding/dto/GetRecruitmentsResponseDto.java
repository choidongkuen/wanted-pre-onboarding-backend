package com.example.pre_onboarding.dto;

import com.example.pre_onboarding.constant.Position;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetRecruitmentsResponseDto {
    private Long id;
    private String companyId;
    private Position position;
    private Integer recruitmentBonus;
    private String recruitmentContent;
    private String skills;
}
