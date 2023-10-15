package com.example.pre_onboarding.exception.recruitment;

import lombok.Getter;

@Getter
public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String message) {
    }
}
