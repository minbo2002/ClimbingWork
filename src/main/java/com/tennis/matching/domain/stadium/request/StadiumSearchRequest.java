package com.tennis.matching.domain.stadium.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StadiumSearchRequest {

    private String name;
    private String content;
}
