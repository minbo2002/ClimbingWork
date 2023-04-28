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
    NOT_FOUND_STADIUM(HttpStatus.NOT_FOUND, "경기장을 찾을수 없습니다");

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
