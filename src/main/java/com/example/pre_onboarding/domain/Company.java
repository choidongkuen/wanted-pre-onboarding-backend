package com.example.pre_onboarding.domain;

import com.example.pre_onboarding.constant.Area;
import com.example.pre_onboarding.constant.Nation;
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
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName; // 회사 이름

    @Enumerated(EnumType.STRING)
    private Nation nation; // 회사 국가

    @Enumerated(EnumType.STRING)
    private Area area; // 회사 지역

    @OneToMany(mappedBy = "company")
    private List<Recruitment> recruitments = new ArrayList<>();

    public void setRecruitment(Recruitment recruitment) {
        this.recruitments.add(recruitment);
    }
}
