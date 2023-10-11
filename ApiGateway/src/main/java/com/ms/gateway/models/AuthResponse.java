package com.ms.gateway.models;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String userId;
    private String refreshToken;
    private String accessToken;
    private long expireAt;
    private Collection<String> authorities;
}
