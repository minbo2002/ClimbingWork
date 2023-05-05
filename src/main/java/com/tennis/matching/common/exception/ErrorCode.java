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
    NOT_FOUND_MATCH(HttpStatus.NOT_FOUND, "매치를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

}
