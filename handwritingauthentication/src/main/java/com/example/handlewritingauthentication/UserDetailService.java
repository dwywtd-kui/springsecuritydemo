package com.example.handlewritingauthentication;

public interface UserDetailService {

    /**
     * 根据用户名称获取用户
     * @param name username
     * @return 用户信息
     */
    UserDetail getUserByName(String name);
}
