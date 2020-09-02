package com.example.handlewritingauthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author hanliukui
 */
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService{

    @Autowired
    private UserDetailService userDetailService;

    @Override
    public UserDetail authentication(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest == null ||
                StringUtils.isEmpty(authenticationRequest.getUsername()) ||
                StringUtils.isEmpty(authenticationRequest.getPassword())
        ){
            throw new RuntimeException("账号和密码不能为空.");
        }

        UserDetail userDetail = userDetailService.getUserByName(authenticationRequest.getUsername());

        if (userDetail ==null){
            throw new RuntimeException("用户不存在.");
        }

        if (!authenticationRequest.getPassword().equals(userDetail.getPassword())){
            throw new RuntimeException("账号和密码不正确.");
        }

        return userDetail;
    }
}
