package com.example.pre_onboarding.exception;

import lombok.Getter;

@Getter
public class NotFoundRecruitmentException extends RuntimeException {
    public NotFoundRecruitmentException(String message) {
        super(message);
    }
}
