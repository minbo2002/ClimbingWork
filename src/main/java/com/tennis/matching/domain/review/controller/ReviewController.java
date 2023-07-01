package com.tennis.matching.domain.review.controller;

import com.tennis.matching.domain.member.entity.Member;
import com.tennis.matching.domain.review.request.ReviewCreateRequest;
import com.tennis.matching.domain.review.response.ReviewResponse;
import com.tennis.matching.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponse> createReview(Authentication authentication,
                                                       @RequestBody @Valid ReviewCreateRequest requestCreateRequest) {
        log.info("ReviewController createReview() run");

        Member member = (Member) authentication.getPrincipal();
        log.info("member: {}", member);

        ReviewResponse review = reviewService.createReview(member, requestCreateRequest);

        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/reviews")
    public ResponseEntity<?> getReviewList() {
        log.info("ReviewController getReviewList() run");

        return new ResponseEntity<>(reviewService.getReviewList(), HttpStatus.OK);
    }

    // 리뷰 삭제
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {

        reviewService.delete(reviewId);

        return ResponseEntity.ok("리뷰 삭제 완료.");
    }
}
