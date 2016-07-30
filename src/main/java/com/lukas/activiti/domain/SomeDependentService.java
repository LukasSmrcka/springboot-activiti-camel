package com.lukas.activiti.domain;

import org.springframework.stereotype.Component;

@Component
public class SomeDependentService {

    long counter;

    public void increment() {
        System.out.println("SomeDependentService run number " + counter);
        counter++;
    }
}
