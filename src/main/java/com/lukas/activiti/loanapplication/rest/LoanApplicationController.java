package com.lukas.activiti.loanapplication.rest;


import com.lukas.activiti.loanapplication.serviceleyer.CreateLoanApplicationCommand;
import com.lukas.activiti.loanapplication.serviceleyer.CreateLoanApplicationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanApplicationController {

    @Autowired
    CreateLoanApplicationHandler createLoanApplicationHandler;

    //will be post in the future
    @RequestMapping("/loan-applications")
    public String createLoanApplication() {

        createLoanApplicationHandler.execute(new CreateLoanApplicationCommand());
        return "Loan application created";
    }
}
