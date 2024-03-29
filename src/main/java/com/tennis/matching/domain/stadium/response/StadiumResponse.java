package com.tennis.matching.domain.stadium.response;

import com.tennis.matching.domain.stadium.entity.Stadium;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StadiumResponse {

    private Long createId;
    private String name;
    private String content;
    private Boolean parking;
    private Boolean rental;
    private String address;
    private Long likeCount;  // 경기장 좋아요 수
    private String singleUrl;
    private String multiUrl;

    @Builder
    public StadiumResponse(Long createId,
                           String name,
                           String content,
                           Boolean parking,
                           Boolean rental,
                           String address,
                           Long likeCount,
                           String singleUrl,
                           String multiUrl) {

        this.createId = createId;
        this.name = name;
        this.content = content;
        this.parking = parking;
        this.rental = rental;
        this.address = address;
        this.likeCount = likeCount;
        this.singleUrl = singleUrl;
        this.multiUrl = multiUrl;
    }

    static public StadiumResponse mapToDto(Stadium stadium) {  // static == static protected (암묵적)

        return StadiumResponse.builder()
                .createId(stadium.getId())
                .name(stadium.getName())
                .content(stadium.getContent())
                .likeCount((long) stadium.getLikes().size())
                .parking(stadium.getParking())
                .rental(stadium.getRental())
                .address(stadium.getAddress())
                .singleUrl(stadium.getSingleUrl())
                .multiUrl(stadium.getMultiUrl())
                .build();
    }

    static public StadiumResponse createMapToDto(Stadium stadium) {  // static == static protected (암묵적)

        return StadiumResponse.builder()
                .createId(stadium.getId())
                .name(stadium.getName())
                .content(stadium.getContent())
                .likeCount(stadium.getLikeCount())
                .parking(stadium.getParking())
                .rental(stadium.getRental())
                .address(stadium.getAddress())
                .singleUrl(stadium.getSingleUrl())
                .multiUrl(stadium.getMultiUrl())
                .build();
    }
}

