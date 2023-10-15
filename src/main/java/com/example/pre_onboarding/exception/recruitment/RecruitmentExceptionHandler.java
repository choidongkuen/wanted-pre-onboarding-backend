package com.example.pre_onboarding.exception.recruitment;

import com.example.pre_onboarding.controller.RecruitmentController;
import com.example.pre_onboarding.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = RecruitmentController.class)
public class RecruitmentExceptionHandler {
    @ExceptionHandler(RecruitmentNotFoundException.class)
    public ResponseEntity<ErrorMessage> recruitmentNotFoundExceptionHandler(
            RecruitmentNotFoundException exception
    ) {
        return ResponseEntity.badRequest().body(ErrorMessage.of(exception, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ErrorMessage> companyNotFoundExceptionHandler(
            CompanyNotFoundException exception
    ) {
        return ResponseEntity.badRequest().body(ErrorMessage.of(exception,HttpStatus.BAD_REQUEST));
    }
}
