package com.example.handlewritingauthentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 认证请求结构
 * @author hanliukui
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    /**用户名*/
    private String username;

    /**登录密码*/
    private String password;
}
