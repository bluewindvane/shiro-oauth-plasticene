package com.maple.oauth.service;

import com.maple.oauth.entity.ClientDetails;
import com.maple.oauth.entity.RefreshToken;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxf
 * @since 2020-08-21
 */
public interface IRefreshTokenService extends IService<RefreshToken> {

    void insertRefreshToken( ClientDetails clientDetails, String generateRefreshToken);

    RefreshToken selectOneByTokenId(String tokenId);

    void deleteByTokenId(String tokenId);
}
