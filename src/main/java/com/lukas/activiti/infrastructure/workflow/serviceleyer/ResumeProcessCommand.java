package com.lukas.activiti.infrastructure.workflow.serviceleyer;


import com.lukas.activiti.infrastructure.base.serviceleyer.ServiceLayerCommand;

public class ResumeProcessCommand extends ServiceLayerCommand {

    private String processId;

    private String messageName;

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcessId() {
        return processId;
    }
}
