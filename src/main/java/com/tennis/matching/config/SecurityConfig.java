package com.tennis.matching.config;

import com.tennis.matching.common.jwt.JwtAccessDeniedHandler;
import com.tennis.matching.common.jwt.JwtAuthenticationEntryPoint;
import com.tennis.matching.common.jwt.JwtSecurityConfig;
import com.tennis.matching.common.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // token을 사용하는 방식이기 때문에 csrf를 disable
                .csrf().disable()

                // 회원로그인 검증 및 토큰발급
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                // Exception 핸들링할때 직접만든 커스텀클래스를 추가
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/member/hello", "/member/authenticate", "/member/signup").permitAll()  // 로그인API, 회원가입API는 토큰이 없는 상태에서 진행하므로 permitAll
                .anyRequest().authenticated()

                // JwtFilter 클래스를 SecurityConfig 클래스에 적용하기 위해 만든
                // UsernamePasswordAuthenticationFilter 보다 먼저 실행되도록하는 addFilterBefore 메서드가 적용된
                // 커스텀클래스인 JwtSecurityConfig 클래스 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring()
                .antMatchers("/stadiums/**")
                .antMatchers("/favicon.ico");
    }
}
