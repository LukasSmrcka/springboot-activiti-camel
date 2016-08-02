package com.lukas.activiti.loanapplication.serviceleyer;

import com.lukas.activiti.infrastructure.base.serviceleyer.WorkflowServiceLayerCommandHandler;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Component
public class CreateLoanApplicationHandler extends WorkflowServiceLayerCommandHandler<CreateLoanApplicationCommand,CreateLoanApplicationResponse> {

    private final static Logger LOGGER = LoggerFactory.getLogger(CreateLoanApplicationHandler.class);

    private final static String LOAN_APP_PROCESS_KEY = "PosLoanApplicationSimple";

    @Autowired
    public CreateLoanApplicationHandler(RuntimeService runtimeService) {
        super(runtimeService);
    }

    @Override
    public CreateLoanApplicationResponse execute(CreateLoanApplicationCommand command) {

        Map<String, Object> inputParams = createInputParamsMap();

        LOGGER.info("Starting synchronous transactional run of ParallelProcess");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(LOAN_APP_PROCESS_KEY, UUID.randomUUID().toString(),inputParams);

        LOGGER.info("Process {} with Id: {}, ProcessInstanceId: {}, BusinessKey: {} was successfully executed.",
                LOAN_APP_PROCESS_KEY,
                processInstance.getId(),
                processInstance.getProcessInstanceId(),
                processInstance.getBusinessKey());
        return new CreateLoanApplicationResponse();
    }

    private Map<String, Object> createInputParamsMap() {
        Map<String,Object> inputParams = new HashMap<>();
        inputParams.put("VerifyDownpayment",true);
        inputParams.put("DownpaymentChargable",true);
        inputParams.put("RiskAssesmentResolution","APPROVED");
        return inputParams;
    }
}
