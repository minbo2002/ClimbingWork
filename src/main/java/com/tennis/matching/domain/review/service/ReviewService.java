package com.tennis.matching.domain.review.service;

import com.tennis.matching.domain.review.request.ReviewCreateRequest;
import com.tennis.matching.domain.review.response.ReviewResponse;
import java.util.List;

public interface ReviewService {

    // 리뷰 생성
    ReviewResponse createReview(String username, Long matchId, ReviewCreateRequest reviewRequest);

    // 리뷰 리스트
    List<ReviewResponse> getReviewList();

    // 리뷰 상세
    ReviewResponse getReview(Long reviewId);

    // 리뷰 삭제
    void delete(String username, Long reviewId);

}
