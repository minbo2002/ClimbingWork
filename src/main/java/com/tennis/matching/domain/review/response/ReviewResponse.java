package com.tennis.matching.domain.review.response;

import com.tennis.matching.domain.match.entity.Match;
import com.tennis.matching.domain.member.entity.Member;
import com.tennis.matching.domain.review.entity.Review;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewResponse {

    private Long createId;
    private Member member;
    private Match match;
    private String content;

    @Builder
    public ReviewResponse(Long createId, Member member, Match match, String content) {
        this.createId = createId;
        this.member = member;
        this.match = match;
        this.content = content;
    }

    static public ReviewResponse mapToDto(Review review) {

        return ReviewResponse.builder()
                .createId(review.getId())
                .member(review.getMember())
                .match(review.getMatch())
                .content(review.getContent())
                .build();
    }
}
