package com.lukas.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class LogActivity implements JavaDelegate {

    long counter = 1;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Logging activity Start");
        System.out.println("LogActivity run number " + counter);
        counter++;
        System.out.println("Logging activity with getCurrentActivityId: " + execution.getCurrentActivityId());
        System.out.println("Logging activity with getId: " + execution.getId());
        System.out.println("Logging activity with getProcessInstanceId: " + execution.getProcessInstanceId());
        System.out.println("Logging activity End");
    }
}
