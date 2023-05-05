package com.tennis.matching.domain.match.controller;

import com.tennis.matching.domain.match.request.MatchCreateRequest;
import com.tennis.matching.domain.match.request.MatchSearchRequest;
import com.tennis.matching.domain.match.request.MatchUpdateRequest;
import com.tennis.matching.domain.match.response.MatchResponse;
import com.tennis.matching.domain.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    // Match 등록 (관리자만 가능)
    @PostMapping("/matches")
    public ResponseEntity<MatchResponse> createMatch(@Valid @RequestBody MatchCreateRequest matchCreateRequest) {
        log.info("MatchController createMatch");

        MatchResponse match = matchService.createMatch(matchCreateRequest);

        return new ResponseEntity<>(match, HttpStatus.CREATED);
    }

    // Match 전제조회(페이징, 검색)
    @GetMapping("/matches")
    public ResponseEntity<Page<MatchResponse>> getList(
                                                 @PageableDefault(page = 0, size = 10) Pageable pageable,
                                                 @Valid MatchSearchRequest searchRequest
                                                ) {
        log.info("MatchController getList");
        Page<MatchResponse> matchList = matchService.getList(pageable, searchRequest);

        return ResponseEntity.ok(matchList);
    }

    // Match 수정
    @PatchMapping("/matches/{matchId}")
    public void updateMatch(@PathVariable Long matchId, @RequestBody MatchUpdateRequest matchUpdateRequest) {

        matchService.update(matchId, matchUpdateRequest);
    }

    // Match 삭제
    @DeleteMapping("/matches/{matchId}")
    public void deleteMatch(@PathVariable Long matchId) {

        matchService.delete(matchId);
    }
}
