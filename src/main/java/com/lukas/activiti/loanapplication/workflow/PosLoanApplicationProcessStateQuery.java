package com.lukas.activiti.loanapplication.workflow;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PosLoanApplicationProcessStateQuery {

    private final static Logger LOGGER = LoggerFactory.getLogger(PosLoanApplicationProcessStateQuery.class);

    private RuntimeService runtimeService;

    @Autowired
    public PosLoanApplicationProcessStateQuery(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public void getProcessState(String processInstanceId) {

        Execution foundExecution = runtimeService.createExecutionQuery()
                .processInstanceId(processInstanceId)
                .activityId("WaitForVerifyDownpaymentInvoke")
                .singleResult();
        final Boolean executionEnd = Optional.ofNullable(foundExecution).map(x -> x.isEnded()).orElse(false);

        LOGGER.info("WaitForVerifyDownpaymentInvoke finished {}", executionEnd);
    }
}
