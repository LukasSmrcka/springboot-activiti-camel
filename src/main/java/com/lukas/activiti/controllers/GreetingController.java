package com.lukas.activiti.controllers;

import org.springframework.web.bind.annotation.RequestMapping;


public class GreetingController {
    @RequestMapping("/greeting")
    public String greeting() {
        System.out.println("Greetings from hello service!!!");
        return "greeting";
    }
}
