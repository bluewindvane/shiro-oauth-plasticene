package com.maple.oauth.model.dto;

import lombok.Data;

@Data
public class TokenDto {

    private String accessToken;

    private String refreshToken;

    private Long expireTime;

}
