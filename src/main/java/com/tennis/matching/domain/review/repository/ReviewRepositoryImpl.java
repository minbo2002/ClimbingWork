package com.tennis.matching.domain.review.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tennis.matching.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;

import static com.tennis.matching.domain.review.entity.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Review findByMemberIdAndMatchId(Long memberId, Long matchId) {
        return queryFactory.selectFrom(review)
                .where(eqMemberId(memberId), eqMatchId(matchId))
                .fetchOne();
    }

    BooleanExpression eqMemberId(Long memberId) {
        if (memberId == null) {
            return null;
        }
        return review.member.id.eq(memberId);
    }

    BooleanExpression eqMatchId(Long matchId) {
        if (matchId == null) {
            return null;
        }
        return review.match.id.eq(matchId);
    }
}
