package com.tennis.matching.domain.reservation.repository;

import com.tennis.matching.domain.reservation.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationRepositoryCustom {

    Reservation findByMemberIdAndMatchId(String username, Long matchId);

    Page<Reservation> findListByMemberId(Pageable pageable, Long memberId);
}
