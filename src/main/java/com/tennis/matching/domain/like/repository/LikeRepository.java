package com.tennis.matching.domain.like.repository;

import com.tennis.matching.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("select l from Like l where l.member.id=:memberId and l.stadium.id=:stadiumId")
    Optional<Like> findByMemberAndStadium(@Param("memberId") Long memberId, @Param("stadiumId") Long stadiumId);

}
