package com.example.pre_onboarding.filter;

import com.example.pre_onboarding.exception.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/** JwtAuthenticationFilter 예외 발생 시 **/
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.error("Authentication Exception Occurs!");
        this.sendErrorMessage(new BadCredentialsException("로그인이 필요합니다.(인증 실패)"),response);
    }

    private void sendErrorMessage(Exception authenticationException,
                                  HttpServletResponse response
    ) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        try {
            OutputStream os = response.getOutputStream(); // 응답 body write
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Seoul")));

            javaTimeModule.addSerializer(localDateTimeSerializer); // 직렬화 방식 add
            ObjectMapper objectMapper = new ObjectMapper().registerModule(javaTimeModule); // LocalDateTime serialize
            objectMapper.writeValue(os, ErrorMessage.of(authenticationException, HttpStatus.UNAUTHORIZED));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
