package com.hanliukui.demo1quitstart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HelloWordController {

    @GetMapping("/hello")
    public String hello(){
        return "hello !!!";
    }
}
