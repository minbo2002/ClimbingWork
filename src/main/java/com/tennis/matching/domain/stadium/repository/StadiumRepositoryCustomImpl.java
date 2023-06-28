package com.tennis.matching.domain.stadium.repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tennis.matching.domain.stadium.request.StadiumSearchRequest;
import com.tennis.matching.domain.stadium.entity.Stadium;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.tennis.matching.domain.stadium.entity.QStadium.stadium;

@RequiredArgsConstructor
public class StadiumRepositoryCustomImpl implements StadiumRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Stadium> findList(Pageable pageable, StadiumSearchRequest searchRequest) {

        List<Stadium> stadiumList = queryFactory
                .selectFrom(stadium)
                // fetch join 외래키 가지고 있는 주 테이블 - 연관 엔티티 가져오면 -> N+1 <-> fetchjoin()
                .where(getSearch(searchRequest))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(stadium.createAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(stadium.count())
                // left join o, fetch join x
                .where(getSearch(searchRequest))
                .from(stadium);
        return PageableExecutionUtils.getPage(stadiumList, pageable, countQuery::fetchOne);
    }

    private BooleanBuilder getSearch(StadiumSearchRequest searchRequest) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        // null 처리
        String name = searchRequest.getName();
        String content = searchRequest.getContent();
        if (!StringUtils.isEmpty(name)) {
            booleanBuilder.and(stadium.name.contains(name));
        }

        if (!StringUtils.isEmpty(content)) {
            booleanBuilder.and(stadium.content.contains(content));
        }
        return booleanBuilder;
    }
}
