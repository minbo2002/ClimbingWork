package com.tennis.matching.domain.review.repository;

import com.tennis.matching.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}

