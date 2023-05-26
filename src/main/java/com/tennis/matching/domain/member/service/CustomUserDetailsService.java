package com.tennis.matching.domain.member.service;

import com.tennis.matching.domain.member.entity.Member;
import com.tennis.matching.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// 스프링시큐리티에서 제공하는 UserDetailsService 인터페이스를 구현
@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    //  org.springframework.security.core.userdetails.UserDetailsService 인터페이스의 loadUserByUsername() 메서드를 Override
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1) 로그인시 DB에서 member정보와 권한정보를 가져온다.
        return memberRepository.findOneWithAuthoritiesByUsername(username)
                .map(member -> createUser(username, member))
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    // 이때 User 클래스는 org.springframework.security.core.userdetails.User 클래스를 사용
    private User createUser(String username, Member member) {

        if (!member.isActivated()) {  // 2) 해당 member가 활성화 상태라면
            throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        // 3) 유저정보와 권한정보를 가지고 User 객체를 생성해서 반환한다.
        return new org.springframework.security.core.userdetails.User(member.getUsername(),
                member.getPassword(),
                grantedAuthorities);
    }
}
