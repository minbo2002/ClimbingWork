package com.tennis.matching.domain.match.service;

import com.tennis.matching.common.exception.CustomException;
import com.tennis.matching.common.exception.ErrorCode;
import com.tennis.matching.domain.match.entity.Match;
import com.tennis.matching.domain.match.entity.MatchGender;
import com.tennis.matching.domain.match.entity.MatchStatus;
import com.tennis.matching.domain.match.repository.MatchRepository;
import com.tennis.matching.domain.match.request.MatchCreateRequest;
import com.tennis.matching.domain.match.response.MatchResponse;
import com.tennis.matching.domain.stadium.entity.Stadium;
import com.tennis.matching.domain.stadium.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class MatchServiceImpl implements MatchService {

    private final StadiumRepository stadiumRepository;
    private final MatchRepository matchRepository;

    // Match 생성
    @Transactional
    @Override
    public MatchResponse createMatch(MatchCreateRequest matchCreateRequest) {
        log.info("createMatch() run");

        Match match = mapToEntity(matchCreateRequest);
        Match saveMatch = matchRepository.save(match);
        MatchResponse matchResponse = MatchResponse.mapToDto(saveMatch);
        log.info("create matchResponse: {}", matchResponse);

        return matchResponse;
    }

    private Match mapToEntity(MatchCreateRequest matchCreateRequest) {

        Stadium stadium = findStadium(matchCreateRequest.getStadiumId());

        return Match.builder()
                .stadium(stadium)
                .matchNum(matchCreateRequest.getMatchNum())
                .applicantNum(0)
                .status(MatchStatus.OPEN)
                .matchGender(MatchGender.valueOf(matchCreateRequest.getMatchGender().toUpperCase()))
                .content(matchCreateRequest.getContent())
                .startAt(matchCreateRequest.getStartAt())
                .matchDay(matchCreateRequest.getStartAt().getDayOfYear())
                .build();
    }

    private Stadium findStadium(Long stadiumId) {

        return stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STADIUM));
    }
}
