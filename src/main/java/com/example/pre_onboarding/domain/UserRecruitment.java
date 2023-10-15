package com.example.pre_onboarding.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/** 유저가 지원한 채용 공고 **/
@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Entity
public class UserRecruitment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Recruitment recruitment;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
