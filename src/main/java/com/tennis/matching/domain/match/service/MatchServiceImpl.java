package com.tennis.matching.domain.match.service;

import com.tennis.matching.common.exception.CustomException;
import com.tennis.matching.common.exception.ErrorCode;
import com.tennis.matching.domain.match.entity.Match;
import com.tennis.matching.domain.match.entity.MatchGender;
import com.tennis.matching.domain.match.entity.MatchStatus;
import com.tennis.matching.domain.match.repository.MatchRepository;
import com.tennis.matching.domain.match.request.MatchCreateRequest;
import com.tennis.matching.domain.match.request.MatchSearchRequest;
import com.tennis.matching.domain.match.request.MatchUpdateRequest;
import com.tennis.matching.domain.match.response.MatchResponse;
import com.tennis.matching.domain.stadium.entity.Stadium;
import com.tennis.matching.domain.stadium.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
        log.info("MatchServiceImpl createMatch() run");

        Match match = mapToEntity(matchCreateRequest);
        Match saveMatch = matchRepository.save(match);
        MatchResponse matchResponse = MatchResponse.mapToDto(saveMatch);
        log.info("create matchResponse: {}", matchResponse);

        return matchResponse;
    }

    // Match 전제조회(페이징, 검색)
    @Override
    public Page<MatchResponse> getList(Pageable pageable, MatchSearchRequest searchRequest) {
        log.info("MatchServiceImpl getList() run");

        Page<Match> matchList = matchRepository.findList(pageable, searchRequest);
        for(Match match : matchList) {
            match.updateStatus();
        }

        return matchList.map(MatchResponse::mapToDto);
    }

    // Match 수정
    @Transactional
    @Override
    public void update(Long matchId, MatchUpdateRequest matchUpdateRequest) {
        log.info("MatchServiceImpl update() run");

        Match match = findMatch(matchId);
        Stadium stadium = findStadium(matchUpdateRequest.getStadiumId());

        match.update(stadium, matchUpdateRequest);
    }

    // Match 삭제
    @Transactional
    @Override
    public void delete(Long matchId) {
        log.info("MatchServiceImpl delete() run");

        Match match = findMatch(matchId);
        matchRepository.delete(match);
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
                //.startAt(matchCreateRequest.getStartAt())
                .startAt(LocalDateTime.of(matchCreateRequest.getStartAt(), LocalTime.now()))
                //.endAt(matchCreateRequest.getEndAt())
                .endAt(LocalDateTime.of(matchCreateRequest.getEndAt(), LocalTime.now()))
                .matchDay(matchCreateRequest.getStartAt().getDayOfYear())
                .build();
    }

    private Stadium findStadium(Long stadiumId) {

        return stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STADIUM));
    }

    private Match findMatch(Long matchId) {

        return matchRepository.findById(matchId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MATCH));
    }
}
