package com.maple.oauth.service;

import com.maple.oauth.entity.ClientDetails;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxf
 * @since 2020-08-21
 */
public interface IClientDetailsService extends IService<ClientDetails> {

    ClientDetails selectByClientIdAndClientSecret(String clientId, String clientSecret);
}
