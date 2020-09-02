package com.example.handlewritingauthentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author hanliukui
 */
@RestController
public class ResourceController {
    private final String SESSION_KEY  = "my_session";

    @GetMapping("/r/1")
    public String resource1(HttpSession session){
        UserDetail userDetail = (UserDetail) session.getAttribute(SESSION_KEY);
        return userDetail.getUsername()+"访问资源111";
    }

    @GetMapping("/r/2")
    public String resource2(HttpSession session){
        UserDetail userDetail = (UserDetail) session.getAttribute(SESSION_KEY);
        return userDetail.getUsername()+"访问资源222";
    }
}
