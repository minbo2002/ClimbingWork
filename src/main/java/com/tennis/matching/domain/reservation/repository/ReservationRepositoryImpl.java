package com.tennis.matching.domain.reservation.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tennis.matching.domain.reservation.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import java.util.List;

import static com.tennis.matching.domain.reservation.entity.QReservation.reservation;
import static com.tennis.matching.domain.match.entity.QMatch.match;
import static com.tennis.matching.domain.stadium.entity.QStadium.stadium;

@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Reservation findByMemberIdAndMatchId(Long memberId, Long matchId) {


        return queryFactory
                .selectFrom(reservation)
                .where(eqMemberId(memberId), eqMatchId(matchId))
                .fetchOne();
    }

    @Override
    public Page<Reservation> findListByMemberId(Pageable pageable, Long memberId) {

        List<Reservation> reservationList = queryFactory
                .selectFrom(reservation)
                .where(eqMemberId(memberId))
                .join(reservation.match, match).fetchJoin()
                .join(match.stadium, stadium).fetchJoin()
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(reservation.match.startAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(reservation.count())
                .from(reservation)
                .where(eqMemberId(memberId));

        return PageableExecutionUtils.getPage(reservationList, pageable, countQuery::fetchOne);
    }

    private BooleanExpression eqMemberId(Long memberId) {
        if (memberId == null) {
            return null;
        }
        return reservation.member.id.eq(memberId);
    }

    private BooleanExpression eqMatchId(Long matchId) {
        if(matchId == null) {
            return null;
        }
        return reservation.match.id.eq(matchId);
    }
}
