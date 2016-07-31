package com.lukas.activiti.loanapplication.rest;

import com.lukas.activiti.loanapplication.serviceleyer.AppendDownpaymentCommand;
import com.lukas.activiti.loanapplication.serviceleyer.AppendDownpaymentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentSimulationController {

    @Autowired
    private AppendDownpaymentHandler appendDownpaymentHandler;

    @RequestMapping("/loan-applications/{id}/simulate-downpayment")
    public String simulatePayment(@PathVariable("id") String applicationId) {

        AppendDownpaymentCommand command = new AppendDownpaymentCommand();
        command.setDownpaymentIdentification(applicationId);
        appendDownpaymentHandler.execute(command);

        return "Downpayment simulated";
    }
}
