package com.tennis.matching.domain.application.controller;

import com.tennis.matching.domain.application.response.ApplicationResponse;
import com.tennis.matching.domain.application.service.ApplicationService;
import com.tennis.matching.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/applications/{matchId}")
    public ApplicationResponse createApplication(@PathVariable Long matchId, Authentication authentication) {
        log.info("ApplicationController createApplication() run");

        // Authentication 객체의 .getPrincipal() 메서드로 현재 세션사용자의 객체를 가져온다
        Member member = (Member) authentication.getPrincipal();
        log.info("member: {} ", member);

        return applicationService.createApplication(matchId, member);
    }

    @DeleteMapping("/applications/{matchId}")
    public ApplicationResponse cancelApplication(@PathVariable Long matchId, Authentication authentication) {
        log.info("ApplicationController cancelApplication() run");

        Member member = (Member) authentication.getPrincipal();
        log.info("member: {} ", member);

        return applicationService.cancelApplication(matchId, member);
    }
}
