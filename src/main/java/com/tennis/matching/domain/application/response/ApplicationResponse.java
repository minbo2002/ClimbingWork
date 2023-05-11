package com.tennis.matching.domain.application.response;

import com.tennis.matching.domain.application.entity.Application;
import com.tennis.matching.domain.member.entity.Member;
import com.tennis.matching.domain.member.response.MemberResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationResponse {

    private Long applicationId;
    private Long matchId;
    private MemberResponse applicant;
    private Integer openOrClosedMatch;
    private String status;

    @Builder
    public ApplicationResponse(Long applicationId, Long matchId, MemberResponse applicant, Integer openOrClosedMatch, String status) {
        this.applicationId = applicationId;
        this.matchId = matchId;
        this.applicant = applicant;
        this.openOrClosedMatch = openOrClosedMatch;
        this.status = status;
    }

    static public ApplicationResponse mapToDto(Application application) {

        return ApplicationResponse.builder()
                .applicationId(application.getId())
                .matchId(application.getMatch().getId())
                .applicant(MemberResponse.mapToDto(application.getMember()))
                .openOrClosedMatch(application.getMatch().openOrClosedMatch())
                .status(application.getMatch().getStatus().toString())
                .build();
    }
}
