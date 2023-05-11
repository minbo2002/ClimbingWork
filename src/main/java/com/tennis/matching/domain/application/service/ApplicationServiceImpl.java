package com.tennis.matching.domain.application.service;

import com.tennis.matching.common.exception.CustomException;
import com.tennis.matching.common.exception.ErrorCode;
import com.tennis.matching.domain.application.entity.Application;
import com.tennis.matching.domain.application.repository.ApplicationRepository;
import com.tennis.matching.domain.application.response.ApplicationResponse;
import com.tennis.matching.domain.match.entity.Match;
import com.tennis.matching.domain.match.entity.MatchGender;
import com.tennis.matching.domain.match.entity.MatchStatus;
import com.tennis.matching.domain.match.repository.MatchRepository;
import com.tennis.matching.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationServiceImpl implements ApplicationService{

    private final MatchRepository matchRepository;
    private final ApplicationRepository applicationRepository;

    // application 생성
    @Transactional
    @Override
    public ApplicationResponse createApplication(Long matchId, Member member) {
        log.info("ApplicationServiceImpl createApplication() run");

        Match match = findMatch(matchId);

        validateApplyMatch(match, member);

        Application application = Application.builder()
                .match(match)
                .member(member)
                .build();

        Application saveApplication = applicationRepository.save(application);
        ApplicationResponse applicationResponse = ApplicationResponse.mapToDto(saveApplication);
        log.info("applicationResponse: {}", applicationResponse);

        match.increaseApplicantNum();
        match.updateStatus();

        return applicationResponse;
    }

    // appplication 취소
    @Transactional
    @Override
    public ApplicationResponse cancelApplication(Long matchId, Member member) {
        log.info("ApplicationServiceImpl cancelApplication() run");

        Match match = findMatch(matchId);

        Application application = applicationRepository.findByMemberIdAndMatchId(member.getId(), matchId);

        applicationRepository.delete(application);

        match.decreaseApplicantNum();
        match.updateStatus();

        return ApplicationResponse.builder()
                .matchId(matchId)
                .openOrClosedMatch(match.openOrClosedMatch())
                .status(match.getStatus().toString())
                .build();
    }

    // 특정회원이 신청한 매치 전체조회
    @Override
    public Page<ApplicationResponse> getListApplications(Pageable pageable, Member member) {

        return applicationRepository.findListByMemberId(pageable, member.getId())
                .map(ApplicationResponse::mapToDto);
    }


    private Match findMatch(Long matchId) {

        return matchRepository.findById(matchId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MATCH));
    }

    private void validateApplyMatch(Match match, Member member) {
        if (!match.getMatchGender().equals(MatchGender.valueOf(member.getGender().toUpperCase()))
                && !match.getMatchGender().equals(MatchGender.ALL)) {
            throw new CustomException(ErrorCode.DIFFERENCE_GENDER);
        }

        if (match.getStatus().equals(MatchStatus.CLOSE)) {
            throw new CustomException(ErrorCode.CLOSE_MATCH);
        }
    }

}
