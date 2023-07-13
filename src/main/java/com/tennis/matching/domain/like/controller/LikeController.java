package com.tennis.matching.domain.like.controller;

import com.tennis.matching.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // 유저가 스타디움을 좋아요 한다 => 좋아요 or 삭제
    @PostMapping("/stadium/{id}")
    public ResponseEntity<?> addLike(@PathVariable("id") Long stadiumId,
                                     @AuthenticationPrincipal User principal
                                     ) {
        log.info("likeController addLike() run");
        log.info("stadiumId: {} ", stadiumId);
        log.info("principal: {} ", principal);
        log.info("username: {} ", principal.getUsername());

        likeService.addLike(principal.getUsername(), stadiumId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
