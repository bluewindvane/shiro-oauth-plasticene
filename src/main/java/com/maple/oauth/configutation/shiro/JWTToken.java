package com.maple.oauth.configutation.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;
    // 秘钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }


    @Override
    public Object getCredentials() {
        return token;
    }
}
