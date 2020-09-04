package com.maple.oauth.service;

import com.maple.oauth.entity.AccessToken;
import com.baomidou.mybatisplus.extension.service.IService;
import com.maple.oauth.entity.ClientDetails;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxf
 * @since 2020-08-21
 */
public interface IAccessTokenService extends IService<AccessToken> {

    void insertToken(String generateToken, String refreshToken, String username, String type, String code, ClientDetails clientDetails);

    void deleteByCode(String code);

    void deleteByRefreshToken(String tokenId);
}
