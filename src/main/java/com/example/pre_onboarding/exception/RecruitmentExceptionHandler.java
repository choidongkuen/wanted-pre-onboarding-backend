package com.example.pre_onboarding.exception;

import com.example.pre_onboarding.controller.RecruitmentController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = RecruitmentController.class)
public class RecruitmentExceptionHandler {
    @ExceptionHandler(NotFoundRecruitmentException.class)
    public ResponseEntity<ErrorMessage> notFoundRecruitmentExceptionHandler(
            NotFoundRecruitmentException exception
    ) {
        return ResponseEntity.badRequest().body(ErrorMessage.of(exception, HttpStatus.BAD_REQUEST));
    }
}
