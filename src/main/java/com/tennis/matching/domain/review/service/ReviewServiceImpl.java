package com.tennis.matching.domain.review.service;

import com.tennis.matching.common.exception.CustomException;
import com.tennis.matching.common.exception.ErrorCode;
import com.tennis.matching.domain.match.entity.Match;
import com.tennis.matching.domain.match.repository.MatchRepository;
import com.tennis.matching.domain.member.entity.Member;
import com.tennis.matching.domain.review.entity.Review;
import com.tennis.matching.domain.review.repository.ReviewRepository;
import com.tennis.matching.domain.review.request.ReviewCreateRequest;
import com.tennis.matching.domain.review.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService{

    private final MatchRepository matchRepository;
    private final ReviewRepository reviewRepository;

    // 리뷰 생성
    @Transactional
    @Override
    public ReviewResponse createReview(Member member, ReviewCreateRequest reviewRequest) {
        log.info("ReviewServiceImpl createReview() run");

        Review review = mapToEntity(member, reviewRequest);
        Review saveReview = reviewRepository.save(review);
        ReviewResponse reviewResponse = ReviewResponse.mapToDto(saveReview);
        log.info("create review: {}", reviewResponse);

        return reviewResponse;
    }

    // 리뷰 보기
    @Override
    public ReviewResponse getReview(Member member, Long matchId) {

        Review review = reviewRepository.findByMemberIdAndMatchId(member.getId(), matchId);

        if (review == null) {
            Match match = findMatch(matchId);

            review = Review.builder()
                    .member(member)
                    .match(match)
                    .build();
        }

        return ReviewResponse.mapToDto(review);
    }

    // 리뷰 삭제
    @Transactional
    @Override
    public void delete(Long reviewId) {
        log.info("ReviewServiceImpl delete() run");

        Review review = findReview(reviewId);

        reviewRepository.delete(review);
    }

    private Review mapToEntity(Member member, ReviewCreateRequest reviewRequest) {

        Match match = findMatch(reviewRequest.getMatchId());

        return Review.builder()
                .member(member)
                .match(match)
                .content(reviewRequest.getContent())
                .build();
    }

    private Match findMatch(Long matchId) {
        return matchRepository.findById(matchId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MATCH));
    }

    private Review findReview(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_REVIEW));
    }
}
