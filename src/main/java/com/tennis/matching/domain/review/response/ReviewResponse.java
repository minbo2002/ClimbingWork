package com.tennis.matching.domain.review.response;

import com.tennis.matching.domain.review.entity.Review;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewResponse {

    private Long id;
    private String memberName;
    private Long matchId;
    private String content;

    @Builder
    public ReviewResponse(Long id, String memberName, Long matchId, String content) {
        this.id = id;
        this.memberName = memberName;
        this.matchId = matchId;
        this.content = content;
    }

    static public ReviewResponse mapToDto(Review review) {

        return ReviewResponse.builder()
                .id(review.getId())
                .memberName(review.getMember().getUsername())
                .matchId(review.getMatch().getId())
                .content(review.getContent())
                .build();
    }
}
