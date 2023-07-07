package com.tennis.matching.common.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// TokenProvider, JwtFilter 클래스를 SecurityConfig 클래스에 적용하기 위한 클래스
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private static final Logger logger = LoggerFactory.getLogger(JwtSecurityConfig.class);

    private final TokenProvider tokenProvider;

    public JwtSecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) {

        JwtFilter customFilter = new JwtFilter(tokenProvider);  // TokenProvider를 주입받고 JwtFilter를 통해서
        logger.info("JwtSecurityConfig 1) customFilter: " + customFilter);

        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);  // addFilterBefore() 메서드를 이용하여 SecurityConfig 로직에 적용할수 있도록 JwtSecurityConfig 클래스를 등록
    }
}
