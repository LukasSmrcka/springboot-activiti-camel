package com.lukas.activiti.loanapplication.serviceleyer;

import com.lukas.activiti.base.WorkflowServiceLayerCommandHandler;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class CreateLoanApplicationHandler extends WorkflowServiceLayerCommandHandler<CreateLoanApplicationCommand,CreateLoanApplicationResponse> {

    @Autowired
    public CreateLoanApplicationHandler(RuntimeService runtimeService) {
        super(runtimeService);
    }

    @Override
    public CreateLoanApplicationResponse execute(CreateLoanApplicationCommand command) {

        System.out.println("-----------------------------------------------------------");
        System.out.println("Starting synchronous transactional run of ParallelProcess");
        System.out.println("-----------------------------------------------------------");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("parallelProcess", UUID.randomUUID().toString());
        String id = processInstance.getId();
        String processInstanceId = processInstance.getProcessInstanceId();
        String businessKey = processInstance.getBusinessKey();
        String tenantId = processInstance.getTenantId();
        System.out.println("-----------------------------------------------------------");
        System.out.println("Process parameters:");
        System.out.println("Id: " + id);
        System.out.println("ProcessInstanceId: " + processInstanceId);
        System.out.println("BusinessKey: " + businessKey);
        System.out.println("TenantId: " + tenantId);
        System.out.println("-----------------------------------------------------------");
        System.out.println("Finishing synchronous transactional run of ParallelProcess");
        System.out.println("-----------------------------------------------------------");

        return new CreateLoanApplicationResponse();
    }
}
