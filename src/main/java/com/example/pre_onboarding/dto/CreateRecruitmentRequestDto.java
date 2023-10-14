package com.example.pre_onboarding.dto;

import com.example.pre_onboarding.constant.Position;
import com.example.pre_onboarding.domain.Recruitment;
import lombok.*;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import java.util.List;


/* 채용 공고 등록을 위한 요청 dto */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecruitmentRequestDto {
    @NotBlank(message = "회사 아이디는 필수 입력값입니다.")
    private String companyId;

    @NotBlank(message = "회사 이름은 필수 입력값입니다.")
    private String companyName;
    private Position position;
    private Integer recruitmentBonus;
    @NotBlank
    @Lob
    private String recruitmentContent;
    private String skills;

    public Recruitment toEntity() {
        return Recruitment.builder()
                .companyId(companyId)
                .companyName(companyName)
                .position(position)
                .recruitmentBonus(recruitmentBonus)
                .recruitmentContent(recruitmentContent)
                .skills(skills)
                .build();
    }
}
