package com.tennis.matching.domain.stadium.service;

import com.tennis.matching.domain.stadium.request.StadiumCreateRequest;
import com.tennis.matching.domain.stadium.response.StadiumCreateResponse;

public interface StadiumService {

    StadiumCreateResponse createStadium(StadiumCreateRequest stadiumRequest);
}
