package com.tennis.matching.domain.match.service;

import com.tennis.matching.domain.match.request.MatchCreateRequest;
import com.tennis.matching.domain.match.request.MatchUpdateRequest;
import com.tennis.matching.domain.match.response.MatchResponse;

public interface MatchService {

    // Match 생성
    MatchResponse createMatch(MatchCreateRequest request);

    // Match 수정
    void update(Long matchId, MatchUpdateRequest matchUpdateRequest);

    // Match 삭제
    void delete(Long matchId);
}
