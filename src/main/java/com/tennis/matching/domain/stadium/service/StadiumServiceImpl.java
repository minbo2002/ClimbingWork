package com.tennis.matching.domain.stadium.service;

import com.tennis.matching.domain.stadium.entity.Stadium;
import com.tennis.matching.domain.stadium.request.StadiumCreateRequest;
import com.tennis.matching.domain.stadium.response.StadiumCreateResponse;
import com.tennis.matching.repository.stadium.StadiumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StadiumServiceImpl implements StadiumService {

    private final StadiumRepository stadiumRepository;

    @Override
    @Transactional
    public StadiumCreateResponse createStadium(StadiumCreateRequest stadiumCreateRequest) {

        Stadium stadium = mapToEntity(stadiumCreateRequest);
        Stadium saveStadium = stadiumRepository.save(stadium);
        StadiumCreateResponse stadiumCreateResponse = StadiumCreateResponse.mapToDto(saveStadium);
        log.info("create stadiumCreateResponse: {}", stadiumCreateResponse);

        return stadiumCreateResponse;
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

}
