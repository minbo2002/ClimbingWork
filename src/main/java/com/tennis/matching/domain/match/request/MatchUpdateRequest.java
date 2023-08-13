package com.tennis.matching.domain.match.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MatchUpdateRequest {

    @NotNull(message = "경기장을 선택하세요")
    private Long stadiumId;

    @NotNull(message = "경기 인원을 입력하세요")
    private Integer matchNum;

    @NotBlank(message = "성별을 입력하세요")
    private String matchGender;

    @NotBlank(message = "안내사항을 입력하세요")
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "경기 시작 날짜 및 시간을 입력하세요")
    private LocalDate startAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "경기 종료 날짜 및 시간을 입력하세요")
    private LocalDate endAt;

    @Builder
    public MatchUpdateRequest(
                              Long stadiumId,
                              Integer matchNum,
                              String matchGender,
                              String content,
                              LocalDate startAt,
                              LocalDate endAt
                             ) {

        this.stadiumId = stadiumId;
        this.matchNum = matchNum;
        this.matchGender = matchGender;
        this.content = content;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
