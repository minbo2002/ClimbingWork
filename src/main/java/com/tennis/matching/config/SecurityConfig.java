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
import org.springframework.web.filter.OncePerRequestFilter;

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
                .formLogin().disable()
                .httpBasic().disable() // rest api만 고려

                // Exception 핸들링할때 직접만든 커스텀클래스를 추가
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
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
                .antMatchers("/member/**")
                .antMatchers("/stadiums/**")
                .antMatchers("/like/**")
                .antMatchers("/matches/**")
                .antMatchers("/applications/**")
                .antMatchers("/reviews/**")
                .antMatchers("/favicon.ico");
    }
}
