package com.tennis.matching.service;

import com.tennis.matching.domain.stadium.entity.Stadium;
import com.tennis.matching.domain.stadium.repository.StadiumRepository;
import com.tennis.matching.domain.stadium.request.StadiumSearchRequest;
import com.tennis.matching.domain.stadium.response.StadiumResponse;
import com.tennis.matching.domain.stadium.service.StadiumServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class StadiumServiceTest {

    @Mock
    private StadiumRepository stadiumRepository;

    @InjectMocks
    private StadiumServiceImpl stadiumService;

    @Test
    @DisplayName("경기장 목록 조회 테스트")
    void testGetStadiumList() {

        // given - precondition or setup
        StadiumSearchRequest stadiumReqDto = new StadiumSearchRequest();
        stadiumReqDto.setName("구로 실내테니스장");
        stadiumReqDto.setContent("운영시간");

        Pageable pageable = Pageable.ofSize(10).withPage(1);

        Stadium stadium1 = Stadium.builder()
                            .id(1L)
                            .name("name1")
                            .content("content1")
                            .address("address1")
                            .likeCount(0L)
                            .parking(true)
                            .rental(true)
                            .singleUrl("singleUrl1")
                            .multiUrl("multiUrl1")
                            .build();

        Stadium stadium2 = Stadium.builder()
                            .id(2L)
                            .name("name2")
                            .content("content2")
                            .address("address2")
                            .likeCount(0L)
                            .parking(false)
                            .rental(false)
                            .singleUrl("singleUrl2")
                            .multiUrl("multiUrl2")
                            .build();

        log.info("stadium1: {}", stadium1);
        log.info("stadium2: {}", stadium2);
        log.info("pageable: {}", pageable);

        when(stadiumRepository.findList(any(pageable.getClass()), any(stadiumReqDto.getClass())))
                .thenReturn(new PageImpl<>(Arrays.asList(stadium1, stadium2)));

        // when - action or the behaviour that we are going test
        Page<StadiumResponse> stadiumList = stadiumService.getList(pageable, stadiumReqDto);
        log.info("stadiumList: {}", stadiumList);

        // then - verify the output
        assertThat(stadiumList.getTotalElements()).isEqualTo(2);
    }
}