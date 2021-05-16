package com.hanliukui.demo2formlogin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class LoginController {

    @GetMapping("/login")
    public String login(){
        System.out.println("111");
        return "111";
    }
}
