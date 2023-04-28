package com.tennis.matching.domain.stadium.service;

import com.tennis.matching.domain.stadium.request.StadiumCreateRequest;
import com.tennis.matching.domain.stadium.request.StadiumUpdateRequest;
import com.tennis.matching.domain.stadium.response.StadiumResponse;

import java.util.List;

public interface StadiumService {

    StadiumResponse createStadium(StadiumCreateRequest stadiumRequest);

    List<StadiumResponse> getAll();

    StadiumResponse getByStadiumId(Long stadiumId);

    void update(Long stadiumId, StadiumUpdateRequest stadiumUpdateRequest);

    void delete(Long stadiumId);
}
