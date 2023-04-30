package com.tennis.matching.domain.stadium.controller;

import com.tennis.matching.domain.stadium.dto.StadiumSearchRequest;
import com.tennis.matching.domain.stadium.request.StadiumCreateRequest;
import com.tennis.matching.domain.stadium.request.StadiumUpdateRequest;
import com.tennis.matching.domain.stadium.response.StadiumResponse;
import com.tennis.matching.domain.stadium.service.StadiumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StadiumController {

    private final StadiumService stadiumService;

    // 경기장 등록 (관리자만 가능)
    @PostMapping("/stadiums")
    public ResponseEntity<StadiumResponse> createStadium(@Valid @RequestBody StadiumCreateRequest stadiumCreateRequest) {

        StadiumResponse stadium = stadiumService.createStadium(stadiumCreateRequest);

        return new ResponseEntity<>(stadium, HttpStatus.CREATED);
    }

    // 경기장 전체조회
//    @GetMapping("/stadiums")
//    public ResponseEntity<List<StadiumResponse>> getAll() {
//
//        return ResponseEntity.ok(stadiumService.getAll());
//    }

    @GetMapping("/stadiums")
    public ResponseEntity<?> getList(
                                     @PageableDefault(page = 0, size = 10) Pageable pageable,
                                     @Valid StadiumSearchRequest searchRequest
                                     ) {
        log.info("stadium controller stadiumList ");
        log.info("searchRequest: {}", searchRequest);
        Page<StadiumResponse> stadiumResponsePage = stadiumService.getList(pageable, searchRequest);
        return ResponseEntity.ok(stadiumResponsePage);
    }

    // 경기장 상세조회
    @GetMapping("/stadiums/{stadiumId}")
    public ResponseEntity<StadiumResponse> getById(@PathVariable Long stadiumId) {

        return ResponseEntity.ok(stadiumService.getByStadiumId(stadiumId));
    }

    // 경기장 수정
    @PatchMapping("/stadiums/{stadiumId}")
    public void update(@PathVariable Long stadiumId, @Valid @RequestBody StadiumUpdateRequest stadiumUpdateRequest) {

        stadiumService.update(stadiumId, stadiumUpdateRequest);
    }

    // 경기장 삭제
    @DeleteMapping("/stadiums/{stadiumId}")
    public void delete(@PathVariable Long stadiumId) {

        stadiumService.delete(stadiumId);
    }

}
