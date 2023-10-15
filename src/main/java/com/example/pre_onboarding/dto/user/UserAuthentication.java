package com.example.pre_onboarding.dto.user;

import com.example.pre_onboarding.domain.User;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserAuthentication extends AbstractAuthenticationToken {
    private final User user;

    // AbstractAuthenticationToken 생성자의 매개변수는 Collection<? extends Authority> authorities 이다.
    public UserAuthentication(User user) {
        super(authorities(user));
        this.user = user;
    }

    private static List<GrantedAuthority> authorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return this.user.getPassword();
    }

    @Override
    public Object getPrincipal() {
        return this.user.getUserId();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    public User getUser() {
        return this.user;
    }
}
