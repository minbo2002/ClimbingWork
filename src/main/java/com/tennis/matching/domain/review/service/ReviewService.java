package com.tennis.matching.domain.review.service;

import com.tennis.matching.domain.member.entity.Member;
import com.tennis.matching.domain.review.request.ReviewCreateRequest;
import com.tennis.matching.domain.review.response.ReviewResponse;

public interface ReviewService {

    // 리뷰 생성
    ReviewResponse createReview(Member member, ReviewCreateRequest reviewRequest);

    // 리뷰 보기
    ReviewResponse getReview(Member member, Long matchId);

    // 리뷰 삭제
    void delete(Long reviewId);
}
