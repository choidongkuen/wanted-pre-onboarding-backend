package com.example.pre_onboarding.exception.user;

import lombok.Getter;

@Getter
public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException(String message) {
        super(message);
    }
}
