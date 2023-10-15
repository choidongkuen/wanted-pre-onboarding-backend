package com.example.pre_onboarding.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor // @ConfigurationBinding 을 위해서 필수
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String bearer;
    private String secret;
    private Long accessExpiration;
    private Long refreshExpiration;
    private String refreshHeader;
}
