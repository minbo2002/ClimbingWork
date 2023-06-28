package com.tennis.matching.domain.reservation.service;

import com.tennis.matching.common.exception.CustomException;
import com.tennis.matching.common.exception.ErrorCode;
import com.tennis.matching.domain.reservation.entity.Reservation;
import com.tennis.matching.domain.reservation.repository.ReservationRepository;
import com.tennis.matching.domain.reservation.response.ReservationResponse;
import com.tennis.matching.domain.match.entity.Match;
import com.tennis.matching.domain.match.entity.MatchGender;
import com.tennis.matching.domain.match.entity.MatchStatus;
import com.tennis.matching.domain.match.repository.MatchRepository;
import com.tennis.matching.domain.member.entity.Member;
import com.tennis.matching.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {

    private final MatchRepository matchRepository;
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;

    // reservation 생성
    @Transactional
    @Override
    public ReservationResponse createReservation(Long matchId, String username) {
        log.info("ReservationServiceImpl createReservation() run");

        Match match = findMatch(matchId);

        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
        
        validateApplyMatch(match, member);

        Reservation reservation = Reservation.builder()
                .match(match)
                .member(member)
                .build();

        Reservation saveReservation = reservationRepository.save(reservation);
        ReservationResponse reservationResponse = ReservationResponse.mapToDto(saveReservation);
        log.info("reservationResponse: {}", reservationResponse);

        match.increaseApplicantNum();
        match.updateStatus();

        return reservationResponse;
    }

    // reservation 취소
    @Transactional
    @Override
    public ReservationResponse cancelReservation(Long matchId, Member member) {
        log.info("ReservationServiceImpl cancelReservation() run");

        Match match = findMatch(matchId);

        Reservation reservation = reservationRepository.findByMemberIdAndMatchId(member.getId(), matchId);

        reservationRepository.delete(reservation);

        match.decreaseApplicantNum();
        match.updateStatus();

        return ReservationResponse.builder()
                .matchId(matchId)
                .openOrClosedMatch(match.openOrClosedMatch())
                .status(match.getStatus().toString())
                .build();
    }

    // 특정회원이 신청한 예약 전체조회
    @Override
    public Page<ReservationResponse> getListReservations(Pageable pageable, Member member) {
        log.info("ReservationServiceImpl getListReservations() run");
        return reservationRepository.findListByMemberId(pageable, member.getId())
                .map(ReservationResponse::mapToDto);
    }
    
    private Match findMatch(Long matchId) {
        return matchRepository.findById(matchId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MATCH));
    }

    private void validateApplyMatch(Match match, Member member) {
        log.info("ReservationServiceImpl validateApplyMatch() run");
        if (!match.getMatchGender().equals(MatchGender.valueOf(member.getGender().toUpperCase()))
                && !match.getMatchGender().equals(MatchGender.ALL)) {
            log.error("difference gender error matchGender: {}, memberGender: {}", match.getMatchGender(), member.getGender());
            throw new CustomException(ErrorCode.DIFFERENCE_GENDER);
        }

        if (match.getStatus().equals(MatchStatus.CLOSE)) {
            log.warn("match is closed");
            throw new CustomException(ErrorCode.CLOSE_MATCH);
        }
    }

}
