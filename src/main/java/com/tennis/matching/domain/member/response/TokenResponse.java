package com.tennis.matching.domain.member.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenResponse {

    private String token;

    @Builder
    public TokenResponse(String token) {
        this.token = token;
    }
}
