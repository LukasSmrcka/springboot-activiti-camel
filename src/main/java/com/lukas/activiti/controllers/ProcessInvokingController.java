package com.lukas.activiti.controllers;


import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("request")
public class ProcessInvokingController {

    @Autowired
    RuntimeService runtimeService;

    @RequestMapping("/start_process")
    public String startProcess() {

        System.out.println("-----------------------------------------------------------");
        System.out.println("Starting synchronous transactional run of ParallelProcess");
        System.out.println("-----------------------------------------------------------");
        runtimeService.startProcessInstanceByKey("parallelProcess");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Finishing synchronous transactional run of ParallelProcess");
        System.out.println("-----------------------------------------------------------");

        return "ParallelProcess started";
    }
}
