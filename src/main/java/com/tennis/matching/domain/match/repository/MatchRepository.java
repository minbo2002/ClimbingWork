package com.tennis.matching.domain.match.repository;

import com.tennis.matching.domain.match.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long>,
                                         MatchRepositoryCustom {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select m from Match m where m.id = :id")
    Optional<Match> findByIdForCreate(@Param("id") Long matchId);
}
