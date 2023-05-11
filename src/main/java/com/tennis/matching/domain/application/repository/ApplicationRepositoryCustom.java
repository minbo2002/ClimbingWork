package com.tennis.matching.domain.application.repository;

import com.tennis.matching.domain.application.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicationRepositoryCustom {

    Application findByMemberIdAndMatchId(Long memberId, Long matchId);

    Page<Application> findListByMemberId(Pageable pageable, Long memberId);
}
