package com.example.pre_onboarding.exception.user;

import com.example.pre_onboarding.controller.UserController;
import com.example.pre_onboarding.exception.DuplicateUserApplyRecruitmentException;
import com.example.pre_onboarding.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFoundExceptionHandler(
            UserNotFoundException exception
    ) {
        return ResponseEntity.badRequest().body(ErrorMessage.of(exception, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<ErrorMessage> passwordNotMatchExceptionHandler(
            PasswordNotMatchException exception
    ) {
        return ResponseEntity.badRequest().body(ErrorMessage.of(exception,HttpStatus.UNAUTHORIZED));
    }

    @ExceptionHandler(DuplicateUserApplyRecruitmentException.class)
    public ResponseEntity<ErrorMessage> duplicationUserApplyRecruitmentExceptionHandler(
            DuplicateUserApplyRecruitmentException exception
    ) {
        return ResponseEntity.badRequest().body(ErrorMessage.of(exception,HttpStatus.BAD_REQUEST));
    }
}
