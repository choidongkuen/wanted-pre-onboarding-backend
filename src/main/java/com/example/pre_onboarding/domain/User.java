package com.example.pre_onboarding.domain;

import com.example.pre_onboarding.constant.Role;
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
@Entity(name = "users")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<UserApplyRecruitment> userApplyRecruitments = new ArrayList<>();

    public void setUserApplyRecruitments(UserApplyRecruitment userApplyRecruitment) {
        this.userApplyRecruitments.add(userApplyRecruitment);
    }
}
