package com.tennis.matching.domain.review.response;

import com.tennis.matching.domain.review.entity.Review;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewResponse {

    private Long id;
    private String writer;
    private Integer score;
    private Long matchId;
    private String content;

    @Builder
    public ReviewResponse(Long id, String writer, Integer score, Long matchId, String content) {
        this.id = id;
        this.writer = writer;
        this.score = score;
        this.matchId = matchId;
        this.content = content;
    }

    static public ReviewResponse mapToDto(Review review) {

        return ReviewResponse.builder()
                .id(review.getId())
                .writer(review.getMember().getUsername())
                .score(review.getScore())
                .matchId(review.getMatch().getId())
                .content(review.getContent())
                .build();
    }
}
