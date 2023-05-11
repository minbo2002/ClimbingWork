package com.tennis.matching.domain.application.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tennis.matching.domain.application.entity.Application;
import com.tennis.matching.domain.application.entity.QApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import java.util.List;

import static com.tennis.matching.domain.application.entity.QApplication.application;
import static com.tennis.matching.domain.match.entity.QMatch.match;
import static com.tennis.matching.domain.stadium.entity.QStadium.stadium;

@RequiredArgsConstructor
public class ApplicationRepositoryImpl implements ApplicationRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Application findByMemberIdAndMatchId(Long memberId, Long matchId) {

        return queryFactory
                .selectFrom(QApplication.application)
                .where(eqMemberId(memberId), eqMatchId(matchId))
                .fetchOne();
    }

    @Override
    public Page<Application> findListByMemberId(Pageable pageable, Long memberId) {

        List<Application> applicationList = queryFactory
                .selectFrom(application)
                .where(eqMemberId(memberId))
                .join(application.match, match).fetchJoin()
                .join(match.stadium, stadium).fetchJoin()
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(application.match.startAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(application.count())
                .from(application)
                .where(eqMemberId(memberId));

        return PageableExecutionUtils.getPage(applicationList, pageable, countQuery::fetchOne);
    }

    private BooleanExpression eqMemberId(Long memberId) {
        if (memberId == null) {
            return null;
        }
        return application.member.id.eq(memberId);
    }

    private BooleanExpression eqMatchId(Long matchId) {
        if(matchId == null) {
            return null;
        }
        return application.match.id.eq(matchId);
    }
}
