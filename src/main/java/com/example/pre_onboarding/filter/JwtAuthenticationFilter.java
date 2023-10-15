package com.example.pre_onboarding.filter;

import com.example.pre_onboarding.config.JwtProperties;
import com.example.pre_onboarding.domain.User;
import com.example.pre_onboarding.dto.user.UserAuthentication;
import com.example.pre_onboarding.exception.user.JwtAuthenticationException;
import com.example.pre_onboarding.repository.UserRepository;
import com.example.pre_onboarding.service.JwtService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * 인증이 필요한 회원 API 요청 시, jwt 인증 용도의 필터
 * - 인증 마다 SecurityContext 생성 후 저장
 **/
@Getter
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (request.getMethod().equals("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            log.info("checkAccessTokenAndAuthentication() called!");
            String accessToken = this.checkAccessTokenAndAuthentication(request);

            if(accessToken == null) {
                throw new JwtAuthenticationException("jwt Authentication exception occurs!");
            }

            // 3. Access Token 을 파싱해서 User 정보 가져오기
            User user = this.userRepository.findByUserId(this.jwtService.getUserIdFromToken(accessToken))
                    .orElseThrow(() -> new UsernameNotFoundException("일치하는 회원이 존재하지 않습니다."));

            // 4. Thread Local 로 동작 하는 SecurityContext 에 저장
            UserAuthentication userAuthentication = new UserAuthentication(user);
            SecurityContextHolder.getContext().setAuthentication(userAuthentication);
        } catch (JwtAuthenticationException | UsernameNotFoundException exception) {
            log.error("JwtAuthentication Authentication Exception Occurs! - {}",exception.getClass());
        }
        filterChain.doFilter(request, response);
        // permitAll() 의 정상적인 처리를 원한다면, 다음 필터로 넘겨 추후 판단하여 ExceptionTranslationFilter 을 무시할지 아니면
        // permitAll() 이 적용되지 않은 API 여서 ExceptionTransliationFilter 을 거칠지 판단
        // 바로 throw 을 하면 ExceptionTranslationFilter 로 처리가 넘어간다.(permitAll 과 상관 없이)
    }

    // 요청 Authorization 헤더 에서 jwt 유효성 검증 후 리턴
    private String checkAccessTokenAndAuthentication(HttpServletRequest request) {

        // 1. HttpServletRequest 에서 Access Token 파싱
        Optional<String> token = this.jwtService.extractAccessToken(request);
        if (token.isEmpty() || !this.jwtService.validateToken(token.get())) {
            return null;
        }
        return token.get();
    }
}