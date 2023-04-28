package com.tennis.matching.domain.stadium.request;

import com.tennis.matching.domain.stadium.entity.Stadium;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class StadiumCreateRequest {

    @NotBlank(message = "경기장 이름을 입력하세요")
    private String name;

    @NotBlank(message = "경기장 특이사항을 입력하세요")
    private String content;

    private Boolean parking;

    private Boolean rental;

    @NotBlank(message = "경기장 주소를 입력하세요")
    private String address;

    @Builder
    public StadiumCreateRequest(String name, String content, Boolean parking, Boolean rental, String address) {
        this.name = name;
        this.content = content;
        this.parking = parking;
        this.rental = rental;
        this.address = address;
    }
}
