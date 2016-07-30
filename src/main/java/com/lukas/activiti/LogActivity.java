package com.lukas.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LogActivity implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("-----------------------------");
        System.out.println("Logging activity Start");
        System.out.println("CurrentActivityId: " + execution.getCurrentActivityId());
        System.out.println("ProcessInstanceId: " + execution.getProcessInstanceId());
        System.out.println("Logging activity End");
        System.out.println("-----------------------------");
    }
}
