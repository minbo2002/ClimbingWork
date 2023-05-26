package com.tennis.matching.domain.member.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class MemberLoginRequest {

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @Builder
    public MemberLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
