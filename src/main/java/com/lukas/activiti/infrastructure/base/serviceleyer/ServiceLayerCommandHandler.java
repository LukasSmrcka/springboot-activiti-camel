package com.lukas.activiti.infrastructure.base.serviceleyer;

public abstract class ServiceLayerCommandHandler<T extends ServiceLayerCommand,R extends ServiceLayerCommandResponse> {
    public abstract R execute(T command);
}
