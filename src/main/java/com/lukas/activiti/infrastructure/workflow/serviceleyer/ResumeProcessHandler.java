package com.lukas.activiti.infrastructure.workflow.serviceleyer;

import com.lukas.activiti.infrastructure.base.serviceleyer.VoidCommandResponse;
import com.lukas.activiti.infrastructure.base.serviceleyer.WorkflowServiceLayerCommandHandler;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResumeProcessHandler extends WorkflowServiceLayerCommandHandler<ResumeProcessCommand,VoidCommandResponse> {

    private final static Logger LOGGER = LoggerFactory.getLogger(ResumeProcessHandler.class);

    @Autowired
    public ResumeProcessHandler(RuntimeService runtimeService) {
        super(runtimeService);
    }

    @Override
    public VoidCommandResponse execute(ResumeProcessCommand command) {
        LOGGER.info("Resuming process with id {} using message: {}", command.getProcessId(), command.getMessageName());
        Execution execution = findExecutionToResume(command);
        runtimeService.messageEventReceived(command.getMessageName(), execution.getId());
        return new VoidCommandResponse();
    }

    private Execution findExecutionToResume(ResumeProcessCommand command) {
        return runtimeService.createExecutionQuery()
                .processInstanceId(command.getProcessId())
                .messageEventSubscriptionName(command.getMessageName())
                .singleResult();
    }
}
