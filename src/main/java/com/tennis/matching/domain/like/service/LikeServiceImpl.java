package com.tennis.matching.domain.like.service;

import com.tennis.matching.common.exception.CustomException;
import com.tennis.matching.common.exception.ErrorCode;
import com.tennis.matching.domain.like.entity.Like;
import com.tennis.matching.domain.like.repository.LikeRepository;
import com.tennis.matching.domain.member.entity.Member;
import com.tennis.matching.domain.stadium.entity.Stadium;
import com.tennis.matching.domain.member.repository.MemberRepository;
import com.tennis.matching.domain.stadium.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final StadiumRepository stadiumRepository;
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;

    @Override
    public void addLike(Long userId, Long stadiumId) {
        log.info("like service add like run...");
        Stadium stadium = stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STADIUM));
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        Optional<Like> likeOptional = likeRepository.findByMemberAndStadium(userId, stadiumId);
        if (likeOptional.isPresent()) {
            likeRepository.delete(likeOptional.get());
        } else {
            likeRepository.save(Like.of(stadium, member));  // 필드가 여러개 of, 필드가 1개 from
        }
    }

}
