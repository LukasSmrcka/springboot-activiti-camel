package com.lukas.activiti.base;

import org.activiti.engine.RuntimeService;

public  abstract class WorkflowServiceLayerCommandHandler<T extends ServiceLayerCommand,R extends ServiceLayerCommandResponse> extends ServiceLayerCommandHandler<T,R> {

    protected final RuntimeService runtimeService;

    public WorkflowServiceLayerCommandHandler(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }
}
