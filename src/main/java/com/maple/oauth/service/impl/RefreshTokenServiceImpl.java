package com.maple.oauth.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maple.oauth.common.exception.BaseException;
import com.maple.oauth.entity.ClientDetails;
import com.maple.oauth.entity.RefreshToken;
import com.maple.oauth.mapper.RefreshTokenMapper;
import com.maple.oauth.service.IRefreshTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Blob;
import java.util.Optional;

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
public class RefreshTokenServiceImpl extends ServiceImpl<RefreshTokenMapper, RefreshToken> implements IRefreshTokenService {

    @Autowired
    private RefreshTokenMapper refreshTokenMapper;

    @Override
    public void insertRefreshToken(ClientDetails clientDetails, String generateRefreshToken) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setTokenId(SecureUtil.md5().digestHex(generateRefreshToken));
        refreshToken.setToken(generateRefreshToken);
        refreshToken.setAuthentication(clientDetails);

        refreshTokenMapper.insert(refreshToken);
    }

    @Override
    public RefreshToken selectOneByTokenId(String tokenId) {
        QueryWrapper<RefreshToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token_id", tokenId);
        queryWrapper.last(" LIMIT 1 ");

        RefreshToken result = Optional.ofNullable(refreshTokenMapper.selectOne(queryWrapper))
                .orElseThrow(() -> {
                    return new BaseException(500, "refresh_token已过期或不存在");
                });

        return result;
    }

    @Override
    public void deleteByTokenId(String tokenId) {
        QueryWrapper<RefreshToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token_id", tokenId);

        refreshTokenMapper.delete(queryWrapper);
    }
}
