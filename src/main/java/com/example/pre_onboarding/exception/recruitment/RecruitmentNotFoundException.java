package com.example.pre_onboarding.exception.recruitment;

import lombok.Getter;

@Getter
public class RecruitmentNotFoundException extends RuntimeException {
    public RecruitmentNotFoundException(String message) {
        super(message);
    }
}
