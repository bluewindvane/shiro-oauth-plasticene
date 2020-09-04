package com.maple.oauth.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maple.oauth.common.api.CommonResult;
import com.maple.oauth.common.enumeration.AccessTokenTypeEnum;
import com.maple.oauth.common.exception.BaseException;
import com.maple.oauth.common.utils.BlobUtil;
import com.maple.oauth.common.utils.JwtTokenUtil;
import com.maple.oauth.entity.ClientDetails;
import com.maple.oauth.entity.Code;
import com.maple.oauth.entity.RefreshToken;
import com.maple.oauth.mapper.CodeMapper;
import com.maple.oauth.model.dto.TokenDto;
import com.maple.oauth.service.*;
import com.maple.oauth.service.component.CodeComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OauthServiceImpl implements IOauthService {

    @Autowired
    private CodeMapper codeMapper;

    @Autowired
    private CodeComponent codeComponent;

    @Autowired
    private IClientDetailsService clientDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IAccessTokenService accessTokenService;

    @Autowired
    private IRefreshTokenService refreshTokenService;

    @Autowired
    private ICodeService codeService;

    @Override
    public CommonResult<String> code(String clientId) {
        //生成不重复的code
        String code = codeComponent.generateCode(clientId);

        //删除该clientId下面其他code
        codeMapper.delete(new QueryWrapper<>(new Code(clientId)));

        //插入数据库
        codeMapper.insert(new Code(code, clientId));

        return CommonResult.success(code);
    }

    @Override
    public CommonResult<TokenDto> token(String clientId, String clientSecret, String code) {
        TokenDto result = new TokenDto();

        //检查code是否存在且在有效期内
        Code oauthCode = Optional.ofNullable(codeMapper.checkClientIdAndCodeInExpire(clientId, code, 600)).orElseThrow(() -> {
            return new BaseException(500, "code 不存在或已过期");
        });

        //检查clientId和clientSecret
        ClientDetails clientDetails = clientDetailsService.selectByClientIdAndClientSecret(clientId, clientSecret);

        //生成token
        String username = clientId;
        String type = AccessTokenTypeEnum.CLIENT_MODE.type;
        Long expireTime = DateTime.now().getTime() + clientDetails.getAccessTokenValidity();
        String generateToken = jwtTokenUtil.generateToken(JwtTokenUtil.generateTenant(type, username), clientDetails.getAccessTokenValidity());
        String generateRefreshToken = jwtTokenUtil.generateToken(JwtTokenUtil.generateTenant(type, username), clientDetails.getAccessTokenValidity() * 3);

        //删除oauth_access_token表 相等code的数据
        accessTokenService.deleteByCode(code);

        //删除oauth_code 表 相等code的数据
        codeService.deleteByCode(code);

        //存入oauth_access_token数据库
        accessTokenService.insertToken(generateToken, generateRefreshToken, username, type, code, clientDetails);

        //存入oauth_refresh_token
        refreshTokenService.insertRefreshToken(clientDetails, generateRefreshToken);

        result.setAccessToken(generateToken);
        result.setRefreshToken(SecureUtil.md5().digestHex(generateRefreshToken));
        result.setExpireTime(expireTime);

        return CommonResult.success(result);
    }

    @Override
    public CommonResult<TokenDto> refreshToken(String tokenId) {
        TokenDto result = new TokenDto();

        //查询refreshToken 是否存在
        RefreshToken refreshToken = refreshTokenService.selectOneByTokenId(tokenId);

        String token = refreshToken.getToken();

        //检查refreshToken是否过期
        if (!jwtTokenUtil.canRefresh(token)) {
            throw new BaseException(500, "token无效已过期");
        }

        //获取实体类
        ClientDetails clientDetails = null;
        Object object = BlobUtil.byteToObject((byte[]) refreshToken.getAuthentication());
        if (object instanceof ClientDetails) {
            clientDetails = (ClientDetails) object;
        }

        //刷新token
        Long expireTime = DateTime.now().getTime() + clientDetails.getAccessTokenValidity();
        String newAccessToken = jwtTokenUtil.refreshToken(token, clientDetails.getAccessTokenValidity());
        String generateRefreshToken = jwtTokenUtil.refreshToken(token, clientDetails.getAccessTokenValidity() * 3);

        //删除oauth_refresh_token是tokenId
        refreshTokenService.deleteByTokenId(tokenId);

        //删除oauth_access_token 表refresh_token 是 tokenId
        accessTokenService.deleteByRefreshToken(tokenId);

        //存入oauth_refresh_token
        refreshTokenService.insertRefreshToken(clientDetails, generateRefreshToken);

        result.setAccessToken(newAccessToken);
        result.setRefreshToken(SecureUtil.md5().digestHex(generateRefreshToken));
        result.setExpireTime(expireTime);

        return CommonResult.success(result);
    }

}
