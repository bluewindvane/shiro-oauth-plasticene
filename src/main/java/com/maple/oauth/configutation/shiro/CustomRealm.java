package com.maple.oauth.configutation.shiro;

import com.maple.oauth.common.exception.BaseException;
import com.maple.oauth.common.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String token = principalCollection.toString();
        simpleAuthorizationInfo.addRole("testRole");
        simpleAuthorizationInfo.addStringPermission("testRolePermission");
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }

        //获取token
        String principal = (String) authenticationToken.getPrincipal();
        String token = (String) authenticationToken.getCredentials();

        //解析token，获取用户信息，获取的到就通过
        String username = jwtTokenUtil.getUserNameFromToken(token);

        return new SimpleAuthenticationInfo(principal, token, "userRealm");
    }

    /**
     * 这里如果不使用shiro自带的AuthenticationToken，例如我使用了JWTToken实现自定义，需要重写，不然会报错
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
}
