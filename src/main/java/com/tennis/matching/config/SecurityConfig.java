package com.tennis.matching.config;

import com.tennis.matching.common.jwt.JwtAccessDeniedHandler;
import com.tennis.matching.common.jwt.JwtAuthenticationEntryPoint;
import com.tennis.matching.common.jwt.JwtSecurityConfig;
import com.tennis.matching.common.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)  // @PreAuthorize 어노테이션을 메소드 단위로 추가하기 위해 적용
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {  // PasswordEncoder는 BCryptPasswordEncoder를 사용
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()   // token을 사용하는 방식이기 때문에 csrf()를 disable
                .formLogin().disable()
                .httpBasic().disable() // rest api만 고려

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)  // Exception 핸들링할때 직접만든 커스텀클래스(JwtAuthenticationEntryPoint)를 추가
                .accessDeniedHandler(jwtAccessDeniedHandler)    // Exception 핸들링할때 직접만든 커스텀클래스(JwtAccessDeniedHandler)를 추가

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)     // 세션을 사용하지 않기 때문에 세션 설정을 STATELESS로 설정

                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/login").permitAll()  // 로그인 api는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll() 설정
                .antMatchers("/member/signup").permitAll()   // 회원가입 api는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll() 설정
                .antMatchers(HttpMethod.GET, "/matches/**").permitAll()
                .antMatchers("/matches/**").hasRole("ADMIN")
                .antMatchers("/stadiums/**").hasRole("ADMIN")
                .antMatchers("/like/**").hasRole("USER")
                .antMatchers("/reviews/**").hasRole("USER")
                .antMatchers("/reservations/**").hasRole("USER")
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));  /* 커스텀 JwtFilter 클래스를 커스텀 SecurityConfig 클래스에 적용하기 위해 만든
                                                                  UsernamePasswordAuthenticationFilter 보다 먼저 실행되도록하는 addFilterBefore() 메서드가 적용된
                                                                  커스텀 "JwtSecurityConfig" 클래스를 적용시킨다. */


        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring()
                .antMatchers("/test/**")
                .antMatchers("/favicon.ico");
    }
}
