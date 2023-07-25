package com.tennis.matching.domain.match.response;

import com.tennis.matching.domain.match.entity.Match;
import com.tennis.matching.domain.stadium.response.StadiumResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MatchResponse {

    private Long createId;
    private StadiumResponse stadium;
    private Integer matchNum;
    private Integer applicantNum;
    private String status;
    private String matchGender;
    private String content;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @Builder
    public MatchResponse(
                         Long createId,
                         StadiumResponse stadium,
                         Integer matchNum,
                         Integer applicantNum,
                         String status,
                         String matchGender,
                         String content,
                         LocalDateTime startAt,
                         LocalDateTime endAt
                        ) {

        this.createId = createId;
        this.stadium = stadium;
        this.matchNum = matchNum;
        this.applicantNum = applicantNum;
        this.status = status;
        this.matchGender = matchGender;
        this.content = content;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    static public MatchResponse mapToDto(Match match) {

            return MatchResponse.builder()
                    .createId(match.getId())
                    .stadium(StadiumResponse.mapToDto(match.getStadium()))
                    .matchNum(match.getMatchNum())
                    .applicantNum(match.getApplicantNum())
                    .status(match.getStatus().toString())
                    .matchGender(match.getMatchGender().toString())
                    .content(match.getContent())
                    .startAt(match.getStartAt())
                    .endAt(match.getEndAt())
                    .build();
    }
}
