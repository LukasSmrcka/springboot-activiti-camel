package com.lukas.activiti.loanapplication.serviceleyer;

import com.lukas.activiti.base.WorkflowServiceLayerCommandHandler;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppendDownpaymentHandler extends WorkflowServiceLayerCommandHandler<AppendDownpaymentCommand,AppendDownpaymentResponse> {

    public static final String DOWNPAYMENT_RECEIVED_MESSAGE = "downpaymentReceivedMessage";

    @Autowired
    public AppendDownpaymentHandler(RuntimeService runtimeService) {
        super(runtimeService);
    }

    @Override
    public AppendDownpaymentResponse execute(AppendDownpaymentCommand command) {
        Execution execution = getExecution(command);
        runtimeService.messageEventReceived(DOWNPAYMENT_RECEIVED_MESSAGE,execution.getId());
        return new AppendDownpaymentResponse();
    }

    private Execution getExecution(AppendDownpaymentCommand command) {
        return runtimeService.createExecutionQuery()
                .processInstanceId(command.getDownpaymentIdentification())
                .messageEventSubscriptionName(DOWNPAYMENT_RECEIVED_MESSAGE)
                .singleResult();
    }
}
