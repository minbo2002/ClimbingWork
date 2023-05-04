package com.tennis.matching.domain.match.repository;

import com.tennis.matching.domain.match.entity.Match;
import com.tennis.matching.domain.match.request.MatchSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatchRepositoryCustom {

    Page<Match> findList(Pageable pageable, MatchSearchRequest searchRequest);
}
