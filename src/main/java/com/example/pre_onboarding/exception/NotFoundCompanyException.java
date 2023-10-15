package com.example.pre_onboarding.exception;

import lombok.Getter;

@Getter
public class NotFoundCompanyException extends RuntimeException {
    public NotFoundCompanyException(String message) {
    }
}
