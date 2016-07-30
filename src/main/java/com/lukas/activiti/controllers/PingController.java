package com.lukas.activiti.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;




@RestController
@Scope("request")
public class PingController {

    @RequestMapping("/ping")
    public String greeting() {

        return "Ping OK";
    }
}
