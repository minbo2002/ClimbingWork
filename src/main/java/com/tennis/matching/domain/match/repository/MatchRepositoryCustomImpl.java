package com.tennis.matching.domain.match.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tennis.matching.domain.match.entity.Match;
import com.tennis.matching.domain.match.request.MatchSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class MatchRepositoryCustomImpl implements MatchRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Match> findList(Pageable pageable, MatchSearchRequest searchRequest) {

        return null;
    }
}
