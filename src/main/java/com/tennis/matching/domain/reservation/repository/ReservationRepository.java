package com.tennis.matching.domain.reservation.repository;

import com.tennis.matching.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long>,
                                               ReservationRepositoryCustom {
}
