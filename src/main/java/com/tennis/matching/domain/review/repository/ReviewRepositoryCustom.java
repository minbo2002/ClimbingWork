package com.tennis.matching.domain.review.repository;

import com.tennis.matching.domain.review.entity.Review;

public interface ReviewRepositoryCustom {

    Review findByMemberIdAndMatchId(Long memberId, Long matchId);
}
