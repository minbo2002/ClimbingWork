package com.tennis.matching.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),

    // stadium
    NOT_FOUND_STADIUM(HttpStatus.NOT_FOUND, "경기장을 찾을수 없습니다"),
    NOT_FOUND_LIKE(HttpStatus.NOT_FOUND, "like를 찾을 수 없습니다."),

    // member
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),

    // match
    DIFFERENCE_GENDER(HttpStatus.NOT_FOUND, "성별 허용이 다른 경기입니다"),
    CLOSE_MATCH(HttpStatus.NOT_FOUND, "이미 마감된 경기입니다."),
    NOT_FOUND_MATCH(HttpStatus.NOT_FOUND, "해당 경기를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

}
