package com.tennis.matching.domain.stadium.service;

import com.tennis.matching.domain.stadium.request.StadiumSearchRequest;
import com.tennis.matching.domain.stadium.request.StadiumCreateRequest;
import com.tennis.matching.domain.stadium.request.StadiumUpdateRequest;
import com.tennis.matching.domain.stadium.response.StadiumResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StadiumService {

    // Stadium 생성
    StadiumResponse createStadium(StadiumCreateRequest stadiumRequest);

    // Stadium 전체조회
    List<StadiumResponse> getAll();

    // Stadium 전체조회(페이징, 검색)
    Page<StadiumResponse> getList(Pageable pageable, StadiumSearchRequest searchRequest);

    // Stadium 상세조회
    StadiumResponse getByStadiumId(Long stadiumId);

    // Stadium 수정
    void update(Long stadiumId, StadiumUpdateRequest stadiumUpdateRequest);

    // Stadium 삭제
    void delete(Long stadiumId);
}
