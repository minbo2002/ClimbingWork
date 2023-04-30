package com.tennis.matching.domain.stadium.service;

import com.tennis.matching.common.exception.CustomException;
import com.tennis.matching.common.exception.ErrorCode;
import com.tennis.matching.domain.stadium.dto.StadiumSearchRequest;
import com.tennis.matching.domain.stadium.entity.Stadium;
import com.tennis.matching.domain.stadium.request.StadiumCreateRequest;
import com.tennis.matching.domain.stadium.request.StadiumUpdateRequest;
import com.tennis.matching.domain.stadium.response.StadiumResponse;
import com.tennis.matching.repository.stadium.StadiumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StadiumServiceImpl implements StadiumService {

    private final StadiumRepository stadiumRepository;

    // Stadium 생성
    @Transactional
    @Override
    public StadiumResponse createStadium(StadiumCreateRequest stadiumCreateRequest) {
        log.info("createStadium() run");

        Stadium stadium = mapToEntity(stadiumCreateRequest);
        Stadium saveStadium = stadiumRepository.save(stadium);
        StadiumResponse stadiumResponse = StadiumResponse.mapToDto(saveStadium);
        log.info("create stadiumCreateResponse: {}", stadiumResponse);

        return stadiumResponse;
    }

    // Stadium 전체조회
    @Override
    public List<StadiumResponse> getAll() {

        List<Stadium> findStadiums = stadiumRepository.findAll();

        return findStadiums.stream()
                .map(StadiumResponse::mapToDto)
                .collect(Collectors.toList());
    }

    // Stadium 전체조회(페이징, 검색)
    @Override
    public Page<StadiumResponse> getList(Pageable pageable, StadiumSearchRequest searchRequest) {
        log.info("getList() run");
        Page<Stadium> stadiumPage = stadiumRepository.findList(pageable, searchRequest);

        return stadiumPage.map(StadiumResponse::mapToDto);
    }

    // Stadium 상세조회
    @Override
    public StadiumResponse getByStadiumId(Long stadiumId) {
        log.info("getByStadiumId() run");

        Stadium findStadium = findStadium(stadiumId);

        return StadiumResponse.mapToDto(findStadium);
    }

    // Stadium 수정
    @Transactional
    @Override
    public void update(Long stadiumId, StadiumUpdateRequest stadiumUpdateRequest) {
        log.info("update() run");

        Stadium findStadium = findStadium(stadiumId);

        findStadium.update(stadiumUpdateRequest);
    }

    // Stadium 삭제
    @Transactional
    @Override
    public void delete(Long stadiumId) {
        log.info("delete() run");

        Stadium findStadium = findStadium(stadiumId);

        stadiumRepository.delete(findStadium);
    }

    private Stadium mapToEntity(StadiumCreateRequest stadiumCreateRequest) {

        return Stadium.builder()
                .name(stadiumCreateRequest.getName())
                .content(stadiumCreateRequest.getContent())
                .parking(stadiumCreateRequest.getParking())
                .rental(stadiumCreateRequest.getRental())
                .address(stadiumCreateRequest.getAddress())
                .build();
    }

    private Stadium findStadium(Long stadiumId) {

        return stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STADIUM));
    }
}
