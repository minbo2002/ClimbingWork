package com.tennis.matching.domain.member.controller;

import com.tennis.matching.domain.member.request.MemberSignUpRequest;
import com.tennis.matching.domain.member.response.MemberResponse;
import com.tennis.matching.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/member/signup")
    public ResponseEntity<MemberResponse> signup(@Valid @RequestBody MemberSignUpRequest memberSignUpRequest) {

        MemberResponse member = memberService.signup(memberSignUpRequest);

        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    // 현재 SecurityContext에 저장되어있는 Member의 username에 해당하는 Member정보와 권한정보를 가져오는 메서드
    @GetMapping("/member/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MemberResponse> getMyUserInfo(HttpServletRequest request) {

        return ResponseEntity.ok(memberService.getMyUserWithAuthorities());
    }

    // 특정 username을 가진 Member정보와 권한정보를 가져오는 메서드
    @GetMapping("/member/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberResponse> getUserInfo(@PathVariable String username) {

        return ResponseEntity.ok(memberService.getUserWithAuthorities(username));
    }
}
