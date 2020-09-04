package com.maple.oauth.service;

import com.maple.oauth.common.api.CommonResult;
import com.maple.oauth.model.dto.TokenDto;

import java.io.IOException;

public interface IOauthService {

    /**
     * 生成code
     *
     * @param clientId
     * @return
     */
    CommonResult<String> code(String clientId);

    /**
     * 生成token
     *
     * @param clientId
     * @param clientSecret
     * @param code
     * @return
     */
    CommonResult<TokenDto> token(String clientId, String clientSecret, String code);

    /**
     * 生成refreshToken
     *
     * @param tokenId
     * @return
     */
    CommonResult<TokenDto> refreshToken(String tokenId);
}
