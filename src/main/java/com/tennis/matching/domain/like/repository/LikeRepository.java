package com.tennis.matching.domain.like.repository;

import com.tennis.matching.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
