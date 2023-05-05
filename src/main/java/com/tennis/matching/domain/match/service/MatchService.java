package com.tennis.matching.domain.match.service;

import com.tennis.matching.domain.match.request.MatchCreateRequest;
import com.tennis.matching.domain.match.request.MatchSearchRequest;
import com.tennis.matching.domain.match.request.MatchUpdateRequest;
import com.tennis.matching.domain.match.response.MatchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatchService {

    // Match 생성
    MatchResponse createMatch(MatchCreateRequest request);

    // Match 전체조회
    Page<MatchResponse> getList(Pageable pageable, MatchSearchRequest searchRequest);

    // Match 수정
    void update(Long matchId, MatchUpdateRequest matchUpdateRequest);

    // Match 삭제
    void delete(Long matchId);
}
