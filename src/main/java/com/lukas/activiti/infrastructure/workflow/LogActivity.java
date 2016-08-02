package com.lukas.activiti.infrastructure.workflow;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LogActivity implements JavaDelegate {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogActivity.class);

    @Override

    @Transactional(propagation = Propagation.REQUIRED)
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Running process with ProcessInstanceId: {}, ExecutionId: {} - Activity with name: {} was executed.",
                execution.getProcessInstanceId(),
                execution.getId(),
                execution.getCurrentActivityId());
    }
}
