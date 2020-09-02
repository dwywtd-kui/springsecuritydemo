package com.example.demo3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
public class LogoutController {

    @RequestMapping("/mylogout")
    public void logout(){
        System.out.println("退出登录----");
    }
}
