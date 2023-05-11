package com.tennis.matching.domain.review.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ReviewCreateRequest {

    private Long matchId;

    @NotNull
    @Size(min=5, message = "리뷰를 입력해주세요(최소 5글자)")
    private String content;
}
