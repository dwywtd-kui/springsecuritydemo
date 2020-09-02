package com.example.handlewritingauthentication;

/**
 * @author hanliukui
 */
public interface UserAuthenticationService {

    /**
     * 进行认证
     * @param authenticationRequest 认证参数
     * @return
     */
    UserDetail authentication(AuthenticationRequest authenticationRequest);
}
