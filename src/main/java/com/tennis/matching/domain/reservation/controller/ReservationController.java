package com.tennis.matching.domain.reservation.controller;

import com.tennis.matching.domain.reservation.response.ReservationResponse;
import com.tennis.matching.domain.reservation.service.ReservationService;
import com.tennis.matching.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservations/{matchId}")
    public ReservationResponse createApplication(@PathVariable Long matchId,
                                                 @AuthenticationPrincipal User principal) {

        log.info("ApplicationController createApplication() run");
        log.info("principal: {} ", principal);
        log.info("member: {} ", principal.getUsername());

        return reservationService.createReservation(matchId, principal.getUsername());
    }

    /*
    @PostMapping("/reservations/{matchId}")
    public ApplicationResponse createApplication(@PathVariable Long matchId, Authentication authentication) {
        log.info("ApplicationController createApplication() run");

        // Authentication 객체의 .getPrincipal() 메서드로 현재 세션사용자의 객체를 가져온다
        Member member = (Member) authentication.getPrincipal();
        log.info("member: {} ", member);

        return applicationService.createApplication(matchId, member);
    }
    */

    @DeleteMapping("/reservations/{matchId}")
    public ReservationResponse cancelApplication(@PathVariable Long matchId,
                                                 @AuthenticationPrincipal Member member) {
        log.info("ApplicationController cancelApplication() run");
        log.info("member: {} ", member);

        return reservationService.cancelReservation(matchId, member);
    }
}
