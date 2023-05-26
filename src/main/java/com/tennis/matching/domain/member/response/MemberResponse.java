package com.tennis.matching.domain.member.response;

import com.tennis.matching.domain.application.entity.Application;
import com.tennis.matching.domain.application.response.ApplicationResponse;
import com.tennis.matching.domain.member.entity.Authority;
import com.tennis.matching.domain.member.entity.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
public class MemberResponse {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String gender;
    private boolean activated;  // 활성화 여부
    Set<Authority> authorities; // 권한

    @Builder
    public MemberResponse(Long id,
                          String username,
                          String password,
                          String nickname,
                          String gender,
                          boolean activated,
                          Set<Authority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.activated = activated;
        this.authorities = authorities;
    }

    static public MemberResponse mapToDto(Member member) {

        return MemberResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .gender(member.getGender())
                .activated(member.isActivated())
                .authorities(member.getAuthorities())
                .build();
    }
}
