package com.tennis.matching.domain.reservation.service;

import com.tennis.matching.domain.member.entity.Member;
import com.tennis.matching.domain.reservation.response.ReservationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationService {

    ReservationResponse createReservation(Long matchId, String username);

    ReservationResponse cancelReservation(Long matchId, String username);

    Page<ReservationResponse> getListReservations(Pageable pageable, Member member);
}
