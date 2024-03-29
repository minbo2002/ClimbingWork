package com.tennis.matching.domain.review.controller;

import com.tennis.matching.domain.review.request.ReviewCreateRequest;
import com.tennis.matching.domain.review.response.ReviewResponse;
import com.tennis.matching.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping("/reviews/{matchId}")
    public ResponseEntity<ReviewResponse> createReview(@AuthenticationPrincipal User principal,
                                                       @RequestBody @Valid ReviewCreateRequest requestCreateRequest,
                                                       @PathVariable Long matchId) {
        log.info("ReviewController createReview() run");
        log.info("principal: {} ", principal);
        log.info("username: {} ", principal.getUsername());
        log.info("matchId: {} ", matchId);

        ReviewResponse review = reviewService.createReview(principal.getUsername(), matchId, requestCreateRequest);

        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    // 리뷰 리스트
    @GetMapping("/reviews")
    public ResponseEntity<ReviewResponse> getReviewList() {
        log.info("ReviewController getReviewList() run");

        List<ReviewResponse> reviewList = reviewService.getReviewList();

        return new ResponseEntity(reviewList, HttpStatus.OK);
    }

    // 리뷰 상세
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable Long reviewId) {
        log.info("ReviewController getReview() run");

        return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);
    }

    // 리뷰 삭제
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<?> deleteReview(@AuthenticationPrincipal User principal,
                                          @PathVariable Long reviewId) {

        log.info("ReviewController deleteReview() run");
        log.info("principal: {} ", principal);
        log.info("member: {} ", principal.getUsername());

        reviewService.delete(principal.getUsername(), reviewId);

        return ResponseEntity.ok("리뷰 삭제 완료.");
    }
}
