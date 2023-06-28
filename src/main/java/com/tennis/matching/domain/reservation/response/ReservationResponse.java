package com.tennis.matching.domain.reservation.response;

import com.tennis.matching.domain.reservation.entity.Reservation;
import com.tennis.matching.domain.member.response.MemberResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationResponse {

    private Long applicationId;
    private Long matchId;
    private MemberResponse applicant;
    private Integer openOrClosedMatch;
    private String status;

    @Builder
    public ReservationResponse(Long applicationId, Long matchId, MemberResponse applicant, Integer openOrClosedMatch, String status) {
        this.applicationId = applicationId;
        this.matchId = matchId;
        this.applicant = applicant;
        this.openOrClosedMatch = openOrClosedMatch;
        this.status = status;
    }

    static public ReservationResponse mapToDto(Reservation reservation) {

        return ReservationResponse.builder()
                .applicationId(reservation.getId())
                .matchId(reservation.getMatch().getId())
                .applicant(MemberResponse.mapToDto(reservation.getMember()))
                .openOrClosedMatch(reservation.getMatch().openOrClosedMatch())
                .status(reservation.getMatch().getStatus().toString())
                .build();
    }
}
