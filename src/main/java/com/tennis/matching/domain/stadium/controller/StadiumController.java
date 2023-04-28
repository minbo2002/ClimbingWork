package com.tennis.matching.domain.stadium.controller;

import com.tennis.matching.domain.stadium.request.StadiumCreateRequest;
import com.tennis.matching.domain.stadium.response.StadiumCreateResponse;
import com.tennis.matching.domain.stadium.service.StadiumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StadiumController {

    private final StadiumService stadiumService;

    @PostMapping("/stadiums")
    public ResponseEntity<StadiumCreateResponse> createStadium(@Valid @RequestBody StadiumCreateRequest stadiumCreateRequest) {

        StadiumCreateResponse stadium = stadiumService.createStadium(stadiumCreateRequest);

        return new ResponseEntity<>(stadium, HttpStatus.CREATED);
    }
}
