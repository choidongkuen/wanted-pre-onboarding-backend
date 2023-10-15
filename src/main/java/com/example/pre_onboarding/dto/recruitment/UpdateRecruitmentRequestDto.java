package com.example.pre_onboarding.dto.recruitment;

import com.example.pre_onboarding.constant.Position;
import lombok.*;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRecruitmentRequestDto {
    @NotBlank(message = "회사 아이디는 필수 입력값입니다.")
    private Position position;
    private Integer recruitmentBonus;
    @NotBlank
    @Lob
    private String recruitmentContent;

    private String skills;
}
