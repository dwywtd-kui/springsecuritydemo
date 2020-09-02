package com.example.handlewritingauthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author hanliukui
 */
@RequestMapping("")
@RestController
public class LoginController {

    private final String SESSION_KEY  = "my_session";

    @Autowired
    private UserAuthenticationService authenticationService;

    /**登录*/
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(AuthenticationRequest authenticationRequest, HttpSession session){
        UserDetail userDetail = authenticationService.authentication(authenticationRequest);
        session.setAttribute(SESSION_KEY,userDetail);
        return userDetail.getUsername()+" 登录成功了.";
    }
    /**等出*/
    @RequestMapping(value = "/logout" ,method = RequestMethod.POST)
    public String logout(HttpSession session){
        UserDetail user = (UserDetail) session.getAttribute(SESSION_KEY);
        session.invalidate();
        return user.getUsername()+"登出了.";
    }


}
