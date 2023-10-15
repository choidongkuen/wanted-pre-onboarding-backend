package com.example.pre_onboarding.exception;

import lombok.Getter;

@Getter
public class DuplicateUserApplyRecruitmentException extends RuntimeException {
    public DuplicateUserApplyRecruitmentException(String message) {
        super(message);
    }
}
