package com.tennis.matching.domain.stadium.repository;

import com.tennis.matching.domain.stadium.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StadiumRepository extends JpaRepository<Stadium, Long>,
                                           StadiumRepositoryCustom {
}
