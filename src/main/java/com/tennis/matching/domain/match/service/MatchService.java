package com.tennis.matching.domain.match.service;

import com.tennis.matching.domain.match.request.MatchCreateRequest;
import com.tennis.matching.domain.match.response.MatchResponse;

public interface MatchService {

    MatchResponse createMatch(MatchCreateRequest request);
}
