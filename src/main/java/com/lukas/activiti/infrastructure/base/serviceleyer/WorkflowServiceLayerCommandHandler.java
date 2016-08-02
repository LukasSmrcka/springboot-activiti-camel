package com.lukas.activiti.infrastructure.base.serviceleyer;

import org.activiti.engine.RuntimeService;

public  abstract class WorkflowServiceLayerCommandHandler<T extends ServiceLayerCommand,R extends ServiceLayerCommandResponse> extends ServiceLayerCommandHandler<T,R> {

    protected final RuntimeService runtimeService;

    public WorkflowServiceLayerCommandHandler(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }
}
