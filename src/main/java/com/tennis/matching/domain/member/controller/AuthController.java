package com.tennis.matching.domain.member.controller;

import com.tennis.matching.common.jwt.JwtFilter;
import com.tennis.matching.common.jwt.TokenProvider;
import com.tennis.matching.domain.member.request.MemberLoginRequest;
import com.tennis.matching.domain.member.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    // JWT 토큰 생성
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authorize(@Valid @RequestBody MemberLoginRequest memberLoginRequest) {
        log.info("AuthController authorize() run");

        // 1) username과 password를 입력해서 authenticationToken 객체를 생성한다.
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberLoginRequest.getUsername(), memberLoginRequest.getPassword());

        // 2) authenticationToken을 이용해서 authenticate() 메서드가 실행될때
        // 커스텀 CustomUserDetailsService 클래스의 loadUserByUsername() 메서드가 실행되고 그 결과값을 가지고 authentication 객체를 생성
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3) authentication 객체를 SecurityContextHolder에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 4) authentication 객체의 인증정보를 기준으로 커스텀 TokenProvider클래스의 createToken() 메서드를 실행해서 JWT Token을 생성
        String jwtToken = tokenProvider.createToken(authentication);

        // 5) 생성한 JWT Token을 Response Header에 넣어서 반환
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwtToken);

        // 6) 생성한 JWT Token을 Response Body에 넣어서 반환
        return new ResponseEntity<>(new TokenResponse(jwtToken), httpHeaders, HttpStatus.OK);
    }
}
