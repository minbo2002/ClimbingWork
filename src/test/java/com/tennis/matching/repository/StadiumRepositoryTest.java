package com.tennis.matching.repository;

import com.tennis.matching.common.exception.CustomException;
import com.tennis.matching.common.exception.ErrorCode;
import com.tennis.matching.domain.stadium.entity.Stadium;
import com.tennis.matching.domain.stadium.repository.StadiumRepository;
import com.tennis.matching.domain.stadium.request.StadiumUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
class StadiumRepositoryTest {

    @Autowired
    private StadiumRepository stadiumRepository;

    private Stadium stadium1, stadium2;

    @BeforeEach
    void setup() {
        stadium1 = Stadium.builder()
                .name("구로 실내테니스장")
                .content("운영시간은 오전 10시부터 오후 8시까지 입니다.")
                .address("서울특별시 구로구 시흥대로 551 광희빌딩 지하2층")
                .likeCount(0L)
                .parking(true)
                .rental(true)
                .build();

        stadium2 = Stadium.builder()
                .name("용인 야외테니스장")
                .content("비오는 날은 운영하지 않습니다.")
                .address("용인시 기흥구 중부대로 100")
                .likeCount(0L)
                .parking(false)
                .rental(true)
                .build();
    }

    @DisplayName("Stadium 생성")
    @Test
    public void testCreateStadium() {

        // given - precondition or setup

        // when - action or the behaviour that we are going test
        Stadium savedStadium1 = stadiumRepository.save(stadium1);
        Stadium savedStadium2 = stadiumRepository.save(stadium2);
        log.info("savedStadium1: {}", savedStadium1);
        log.info("savedStadium2: {}", savedStadium2);

        // then - verify the output
        assertThat(savedStadium1.getId()).isNotNull();
        assertThat(savedStadium1.getName()).isEqualTo("구로 실내테니스장");
        assertThat(savedStadium1.getParking()).isEqualTo(true);

        assertThat(savedStadium2.getId()).isNotNull();
        assertThat(savedStadium2.getName()).contains("용인");
        assertThat(savedStadium2.getRental()).isEqualTo(true);
    }

    @DisplayName("Stadium 단일조회")
    @Test
    public void testGetStadium() {

        // given - precondition or setup

        // when - action or the behaviour that we are going test
        Stadium savedStadium1 = stadiumRepository.save(stadium1);
        log.info("savedStadium1: {}", savedStadium1);

        Stadium findStadium = stadiumRepository.findById(savedStadium1.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STADIUM));
        log.info("findStadium: {}", findStadium);

        // then - verify the output
        assertThat(findStadium).isNotNull();
        assertThat(findStadium.getName()).isEqualTo("구로 실내테니스장");

    }

    @DisplayName("Stadium 리스트조회")
    @Test
    public void testGetStadiumList() {

        // given - precondition or setup

        // when - action or the behaviour that we are going test
        stadiumRepository.save(stadium1);
        stadiumRepository.save(stadium2);

        List<Stadium> stadiums = stadiumRepository.findAll();
        log.info("stadiums: {}", stadiums);

        // then - verify the output
        assertThat(stadiums).isNotNull();
        assertThat(stadiums.size()).isEqualTo(45);

    }

    @DisplayName("Stadium 수정")
    @Test
    public void testUpdateStadium() {

        // given - precondition or setup

        // when - action or the behaviour that we are going test
        Stadium savedStadium1 = stadiumRepository.save(stadium1);

        Stadium findStadium = stadiumRepository.findById(savedStadium1.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STADIUM));
        log.info("findStadium: {}", findStadium);

        findStadium.update(StadiumUpdateRequest.builder()
                              .name("관악구 실내테니스장")
                              .parking(false)
                              .rental(false)
                              .build());
        log.info("updateStadium: {}", findStadium);

        // then - verify the output
        assertThat(findStadium).isNotNull();
        assertThat(findStadium.getName()).isEqualTo("관악구 실내테니스장");
    }

    @DisplayName("Stadium 삭제")
    @Test
    public void testDeleteStadium() {

        // given - precondition or setup

        // when - action or the behaviour that we are going test
        Stadium saveStadium = stadiumRepository.save(stadium1);

        Stadium findStadium = stadiumRepository.findById(saveStadium.getId()).get();
        stadiumRepository.delete(findStadium);

        // then - verify the output
        assertThat(stadiumRepository.findById(saveStadium.getId())).isEmpty();
    }

}