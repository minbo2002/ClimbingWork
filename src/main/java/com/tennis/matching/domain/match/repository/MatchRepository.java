package com.tennis.matching.domain.match.repository;

import com.tennis.matching.domain.match.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
