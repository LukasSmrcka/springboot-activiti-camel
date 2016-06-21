package io.fourfinanceit.activiti.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Lukas on 5.6.2016.
 */

@Component
//@Scope("request")
public class SomeDependentService {

    long counter;

    public void increment() {
        System.out.println("SomeDependentService run number " + counter);
        counter++;
    }
}
