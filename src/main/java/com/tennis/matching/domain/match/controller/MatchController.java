package com.tennis.matching.domain.match.controller;

import com.tennis.matching.domain.match.request.MatchCreateRequest;
import com.tennis.matching.domain.match.response.MatchResponse;
import com.tennis.matching.domain.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    // 매치 등록 (관리자만 가능)
    @PostMapping("/matches")
    public ResponseEntity<MatchResponse> createMatch(@Valid @RequestBody MatchCreateRequest matchCreateRequest) {

        MatchResponse match = matchService.createMatch(matchCreateRequest);

        return new ResponseEntity<>(match, HttpStatus.CREATED);
    }
}
