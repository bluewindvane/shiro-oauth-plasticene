package com.maple.oauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectOne;
import com.maple.oauth.common.exception.BaseException;
import com.maple.oauth.entity.ClientDetails;
import com.maple.oauth.mapper.ClientDetailsMapper;
import com.maple.oauth.service.IClientDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ClientDetailsServiceImpl extends ServiceImpl<ClientDetailsMapper, ClientDetails> implements IClientDetailsService {

    @Autowired
    private ClientDetailsMapper clientDetailsMapper;

    @Override
    public ClientDetails selectByClientIdAndClientSecret(String clientId, String clientSecret) {
        QueryWrapper<ClientDetails> clientDetailsQueryWrapper = new QueryWrapper<>();

        clientDetailsQueryWrapper.eq("client_id", clientId);
        clientDetailsQueryWrapper.eq("client_secret", clientSecret);
        clientDetailsQueryWrapper.last(" LIMIT 1 ");

        ClientDetails clientDetails = Optional.ofNullable(clientDetailsMapper.selectOne(clientDetailsQueryWrapper)).orElseThrow(() -> {
            return new BaseException(500, "未查询到客户端信息");
        });

        return clientDetails;
    }
}
