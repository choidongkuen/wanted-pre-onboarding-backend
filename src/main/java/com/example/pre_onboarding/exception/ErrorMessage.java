package com.example.pre_onboarding.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorMessage {
    private final int code;
    private final String errorSimpleName;
    private final String msg;
    private final LocalDateTime timestamp;

    public ErrorMessage(Exception exception, HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.errorSimpleName = exception.getClass().getSimpleName();
        this.msg = exception.getMessage();
        this.timestamp = LocalDateTime.now();
    }

    public static ErrorMessage of(Exception exception, HttpStatus httpStatus) {
        return new ErrorMessage(exception,httpStatus);
    }
}
