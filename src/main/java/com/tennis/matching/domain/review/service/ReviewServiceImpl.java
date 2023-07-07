package com.tennis.matching.domain.review.service;

import com.tennis.matching.common.exception.CustomException;
import com.tennis.matching.common.exception.ErrorCode;
import com.tennis.matching.domain.match.entity.Match;
import com.tennis.matching.domain.match.repository.MatchRepository;
import com.tennis.matching.domain.member.entity.Member;
import com.tennis.matching.domain.member.repository.MemberRepository;
import com.tennis.matching.domain.review.entity.Review;
import com.tennis.matching.domain.review.repository.ReviewRepository;
import com.tennis.matching.domain.review.request.ReviewCreateRequest;
import com.tennis.matching.domain.review.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService{

    private final MatchRepository matchRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    // 리뷰 생성
    @Transactional
    @Override
    public ReviewResponse createReview(String username, ReviewCreateRequest reviewRequest) {
        log.info("ReviewServiceImpl createReview() run");

        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        Review review = mapToEntity(member, reviewRequest);
        Review saveReview = reviewRepository.save(review);
        ReviewResponse reviewResponse = ReviewResponse.mapToDto(saveReview);
        log.info("create review: {}", reviewResponse);

        return reviewResponse;
    }

    // 리뷰 리스트
    @Override
    public List<ReviewResponse> getReviewList() {

        return reviewRepository.findAll().stream()
                .map(ReviewResponse::mapToDto)
                .collect(Collectors.toList());
    }

    // 리뷰 상세
    @Override
    public ReviewResponse getReview(Long reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_REVIEW));

        return ReviewResponse.mapToDto(review);
    }

    // 리뷰 삭제
    @Transactional
    @Override
    public void delete(String username, Long reviewId) {
        log.info("ReviewServiceImpl delete() run");

        Review review = findReview(reviewId);

        log.info("username: {}", username);
        log.info("review.getMember().getUsername(): {}", review.getMember().getUsername());

        if(!review.getMember().getUsername().equals(username)) {
            throw new CustomException(ErrorCode.NOT_MATCH_MEMBER);
        }

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
