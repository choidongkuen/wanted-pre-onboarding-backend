package com.example.pre_onboarding.domain;
import com.example.pre_onboarding.constant.Position;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Entity(name = "recruitments")
public class Recruitment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String companyId; // 회사 고유 id

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Position position; // 채용 포지션

    private Integer recruitmentBonus; // 채용 보상금

    @Lob
    @Column(nullable = false)
    private String recruitmentContent; // 채용 내용

    @OneToMany(mappedBy = "recruitment")
    private List<Skill> skills = new ArrayList<>(); // 사용 기
}
