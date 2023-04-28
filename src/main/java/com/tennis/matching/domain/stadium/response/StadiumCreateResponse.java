package com.tennis.matching.domain.stadium.response;

import com.tennis.matching.domain.stadium.entity.Stadium;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StadiumCreateResponse {

    private Long createId;
    private String name;
    private String content;
    private Boolean parking;
    private Boolean rental;
    private String address;
    private Long likeCount;

    @Builder
    public StadiumCreateResponse(Long createId, String name, String content, Boolean parking, Boolean rental, String address, Long likeCount) {
        this.createId = createId;
        this.name = name;
        this.content = content;
        this.parking = parking;
        this.rental = rental;
        this.address = address;
        this.likeCount = likeCount;
    }

    static public StadiumCreateResponse mapToDto(Stadium stadium) {  // static == static protected (암묵적)

        return StadiumCreateResponse.builder()
                .createId(stadium.getId())
                .name(stadium.getName())
                .content(stadium.getContent())
                .parking(stadium.getParking())
                .rental(stadium.getRental())
                .address(stadium.getAddress())
                .likeCount(stadium.getLikeCount())
                .build();
    }
}

