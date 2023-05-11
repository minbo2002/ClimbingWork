package com.tennis.matching.domain.application.service;

import com.tennis.matching.domain.application.response.ApplicationResponse;
import com.tennis.matching.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicationService {

    ApplicationResponse createApplication(Long matchId, Member member);

    ApplicationResponse cancelApplication(Long matchId, Member member);

    Page<ApplicationResponse> getListApplications(Pageable pageable, Member member);
}
