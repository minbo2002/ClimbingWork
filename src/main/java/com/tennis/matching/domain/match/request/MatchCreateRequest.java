package com.tennis.matching.domain.match.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MatchCreateRequest {

    @NotNull(message = "경기장을 선택하세요")
    private Long stadiumId;

    @NotNull(message = "경기 인원을 입력하세요")
    private Integer matchNum;

    @NotBlank(message = "성별을 입력하세요")
    private String matchGender;

    @NotBlank(message = "안내사항을 입력하세요")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "경기 시작 날짜 및 시간을 입력하세요")
    private LocalDateTime startAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "경기 종료 날짜 및 시간을 입력하세요")
    private LocalDateTime endAt;

    @Builder
    public MatchCreateRequest(
                              Long stadiumId,
                              Integer matchNum,
                              String matchGender,
                              String content,
                              LocalDateTime startAt,
                              LocalDateTime endAt
                             ) {

        this.stadiumId = stadiumId;
        this.matchNum = matchNum;
        this.matchGender = matchGender;
        this.content = content;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
