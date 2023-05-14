package com.tennis.matching.domain.stadium.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class StadiumUpdateRequest {

    @NotBlank(message = "경기장 이름을 입력하세요")
    private String name;

    @NotBlank(message = "경기장 특이사항을 입력하세요")
    private String content;

    private Boolean parking;

    private Boolean rental;

    @NotBlank(message = "경기장 주소를 입력하세요")
    private String address;

    private MultipartFile changeSingleFile;

    @Builder
    public StadiumUpdateRequest(String name, String content, Boolean parking, Boolean rental, String address) {
        this.name = name;
        this.content = content;
        this.parking = parking;
        this.rental = rental;
        this.address = address;
    }
}
