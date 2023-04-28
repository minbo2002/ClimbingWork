package com.tennis.matching.domain.stadium.service;

import com.tennis.matching.common.exception.CustomException;
import com.tennis.matching.common.exception.ErrorCode;
import com.tennis.matching.domain.stadium.entity.Stadium;
import com.tennis.matching.domain.stadium.request.StadiumCreateRequest;
import com.tennis.matching.domain.stadium.request.StadiumUpdateRequest;
import com.tennis.matching.domain.stadium.response.StadiumResponse;
import com.tennis.matching.repository.stadium.StadiumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StadiumServiceImpl implements StadiumService {

    @Transactional
    @Override
    public StadiumResponse createStadium(StadiumCreateRequest stadiumCreateRequest) {

        Stadium stadium = mapToEntity(stadiumCreateRequest);
        Stadium saveStadium = stadiumRepository.save(stadium);
        StadiumResponse stadiumResponse = StadiumResponse.mapToDto(saveStadium);
        log.info("create stadiumCreateResponse: {}", stadiumResponse);

        return stadiumResponse;
    }

    private final StadiumRepository stadiumRepository;

    @Override
    public List<StadiumResponse> getAll() {

        List<Stadium> findStadiums = stadiumRepository.findAll();

        return findStadiums.stream()
                .map(StadiumResponse::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StadiumResponse getByStadiumId(Long stadiumId) {

        Stadium findStadium = findStadium(stadiumId);

        return StadiumResponse.mapToDto(findStadium);
    }

    @Transactional
    @Override
    public void update(Long stadiumId, StadiumUpdateRequest stadiumUpdateRequest) {

        Stadium findStadium = findStadium(stadiumId);

        findStadium.update(stadiumUpdateRequest);
    }

    @Transactional
    @Override
    public void delete(Long stadiumId) {

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
