package com.tennis.matching.domain.like.service;

import org.springframework.stereotype.Service;

public interface LikeService {

    void addLike(Long userId, Long stadiumId);
}
