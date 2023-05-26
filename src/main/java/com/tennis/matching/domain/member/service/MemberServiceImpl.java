package com.tennis.matching.domain.member.service;

import com.tennis.matching.common.exception.CustomException;
import com.tennis.matching.common.exception.ErrorCode;
import com.tennis.matching.domain.member.entity.Authority;
import com.tennis.matching.domain.member.entity.Member;
import com.tennis.matching.domain.member.repository.MemberRepository;
import com.tennis.matching.domain.member.request.MemberSearchRequest;
import com.tennis.matching.domain.member.request.MemberSignUpRequest;
import com.tennis.matching.domain.member.response.MemberResponse;
import com.tennis.matching.domain.stadium.entity.Stadium;
import com.tennis.matching.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    @Override
    public MemberResponse signup(MemberSignUpRequest memberSignUpRequest) {
        if (memberRepository.findOneWithAuthoritiesByUsername(memberSignUpRequest.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .username(memberSignUpRequest.getUsername())
                .password(passwordEncoder.encode(memberSignUpRequest.getPassword()))
                .nickname(memberSignUpRequest.getNickname())
                .gender(memberSignUpRequest.getGender())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        Member saveMember = memberRepository.save(member);
        MemberResponse memberResponse = MemberResponse.mapToDto(saveMember);

        return memberResponse;
    }

    // 특정 username을 가진 Member정보와 권한정보를 가져오는 메서드
    @Override
    public MemberResponse getUserWithAuthorities(String username) {

        Member findMember = memberRepository.findOneWithAuthoritiesByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        return MemberResponse.mapToDto(findMember);
    }

    // 현재 SecurityContext에 저장되어있는 Member의 username에 해당하는 Member정보와 권한정보를 가져오는
    @Override
    public MemberResponse getMyUserWithAuthorities() {

        return MemberResponse.mapToDto(
                    SecurityUtil.getCurrentUsername()
                        .flatMap(memberRepository::findOneWithAuthoritiesByUsername)
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER)));
    }
}
