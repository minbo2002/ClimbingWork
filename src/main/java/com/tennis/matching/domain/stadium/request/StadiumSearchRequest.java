package com.tennis.matching.domain.stadium.request;

import lombok.*;

@Data
@NoArgsConstructor
public class StadiumSearchRequest {

    private String name;
    private String content;

    @Builder
    public StadiumSearchRequest(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
