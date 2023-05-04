package com.tennis.matching.domain.match.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MatchSearchRequest {

    private LocalDate matchDay;
    private String gender;
    private String matchStatus;
    private Integer personnel;
    private String stadiumName;

    @Builder
    public MatchSearchRequest(LocalDate matchDay, String gender, String matchStatus, Integer personnel, String stadiumName) {
        this.matchDay = matchDay;
        this.gender = gender;
        this.matchStatus = matchStatus;
        this.personnel = personnel;
        this.stadiumName = stadiumName;
    }
}
