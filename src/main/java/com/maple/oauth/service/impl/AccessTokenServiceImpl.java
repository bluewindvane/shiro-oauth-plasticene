package com.maple.oauth.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maple.oauth.entity.AccessToken;
import com.maple.oauth.entity.ClientDetails;
import com.maple.oauth.mapper.AccessTokenMapper;
import com.maple.oauth.service.IAccessTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yxf
 * @since 2020-08-21
 */
@Service
@Transactional
public class AccessTokenServiceImpl extends ServiceImpl<AccessTokenMapper, AccessToken> implements IAccessTokenService {

    @Autowired
    private AccessTokenMapper accessTokenMapper;

    @Override
    public void insertToken(String generateToken, String refreshToken, String username, String type, String code, ClientDetails clientDetails) {
        AccessToken accessToken = new AccessToken();

        accessToken.setCode(code);
        accessToken.setUsername(username);
        accessToken.setType(type);
        accessToken.setAuthentication(clientDetails);
        accessToken.setRefreshToken(SecureUtil.md5().digestHex(refreshToken));
        accessToken.setTokenId(SecureUtil.md5().digestHex(generateToken));

        accessTokenMapper.insert(accessToken);
    }

    @Override
    public void deleteByCode(String code) {
        QueryWrapper<AccessToken> accessTokenQueryWrapper = new QueryWrapper<>();
        accessTokenQueryWrapper.eq("code", code);

        accessTokenMapper.delete(accessTokenQueryWrapper);
    }

    @Override
    public void deleteByRefreshToken(String tokenId) {
        QueryWrapper<AccessToken> accessTokenQueryWrapper = new QueryWrapper<>();
        accessTokenQueryWrapper.eq("refresh_token", tokenId);

        accessTokenMapper.delete(accessTokenQueryWrapper);
    }
}
