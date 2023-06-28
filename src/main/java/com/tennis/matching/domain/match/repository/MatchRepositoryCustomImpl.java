package com.tennis.matching.domain.match.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tennis.matching.domain.match.entity.Match;
import com.tennis.matching.domain.match.entity.MatchGender;
import com.tennis.matching.domain.match.entity.MatchStatus;
import com.tennis.matching.domain.match.request.MatchSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import java.time.LocalDate;
import java.util.List;
import static com.tennis.matching.domain.match.entity.QMatch.match;

@RequiredArgsConstructor
public class MatchRepositoryCustomImpl implements MatchRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Match> findList(Pageable pageable, MatchSearchRequest searchRequest) {

        List<Match> matchList = queryFactory
                .selectFrom(match)
                .where(eqStartAt(searchRequest.getMatchDay()),
                       eqGender(searchRequest.getGender()),
                       eqStatus(searchRequest.getMatchStatus()),
                       eqPersonnel(searchRequest.getPersonnel()),
                       eqStadiumName(searchRequest.getStadiumName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(match.startAt.asc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(match.count())
                .from(match)
                .where(eqStartAt(searchRequest.getMatchDay()),
                       eqGender(searchRequest.getGender()),
                       eqStatus(searchRequest.getMatchStatus()),
                       eqPersonnel(searchRequest.getPersonnel()),
                       eqStadiumName(searchRequest.getStadiumName()));

        return PageableExecutionUtils.getPage(matchList, pageable, countQuery::fetchOne);
    }

    private BooleanExpression eqStadiumName(String stadiumName) {
        if (stadiumName == null || stadiumName.isEmpty()) {
            return null;
        }
        return match.stadium.name.eq(stadiumName);
    }

    private BooleanExpression eqStartAt(LocalDate matchDay) {
        if (matchDay == null) {
            return null;
        }
        return match.matchDay.eq(matchDay.getDayOfYear());
    }

    private BooleanExpression eqGender(String gender) {
        if (gender == null || gender.isEmpty()) {
            return null;
        }
        return match.matchGender.eq(MatchGender.valueOf(gender.toUpperCase()));
    }

    private BooleanExpression eqStatus(String status) {
        if (status == null || status.isEmpty()) {
            return null;
        }
        return match.status.eq(MatchStatus.valueOf(status.toUpperCase()));
    }

    private BooleanExpression eqPersonnel(Integer personnel) {
        if (personnel == null) {
            return null;
        }
        return match.matchNum.eq(personnel);
    }
}
