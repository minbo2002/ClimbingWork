package com.tennis.matching.domain.member.service;

import com.tennis.matching.domain.member.request.MemberSignUpRequest;
import com.tennis.matching.domain.member.response.MemberResponse;

public interface MemberService {

    // 회원가입
    MemberResponse signup(MemberSignUpRequest memberSignUpRequest);

    // 특정 username을 가진 Member정보와 권한정보를 가져오는 메서드
    MemberResponse getUserWithAuthorities(String username);

    // 현재 SecurityContext에 저장되어있는 Member의 username에 해당하는 Member정보와 권한정보를 가져오는
    MemberResponse getMyUserWithAuthorities();
}
