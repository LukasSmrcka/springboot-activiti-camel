package com.lukas.activiti.loanapplication.rest;


import com.lukas.activiti.loanapplication.workflow.PosLoanApplicationProcessStateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrentLoanApplicationProcessStateController {

    @Autowired
    PosLoanApplicationProcessStateQuery posLoanApplicationProcessStateQuery;

    @RequestMapping("/workflow/pos-loan-application/{processInstanceId}")
    public String get(@PathVariable String processInstanceId) {

        posLoanApplicationProcessStateQuery.getProcessState(processInstanceId);
        return "State queried";
    }


}
