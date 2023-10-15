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
    public ResponseEntity<ErrorMessage> notFoundRecruitmentExceptionHandler(
            RecruitmentNotFoundException exception
    ) {
        return ResponseEntity.badRequest().body(ErrorMessage.of(exception, HttpStatus.BAD_REQUEST));
    }
}
