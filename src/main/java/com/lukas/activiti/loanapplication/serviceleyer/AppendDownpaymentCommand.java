package com.lukas.activiti.loanapplication.serviceleyer;


import com.lukas.activiti.base.ServiceLayerCommand;

public class AppendDownpaymentCommand extends ServiceLayerCommand{

    private String downpaymentIdentification;

    public void setDownpaymentIdentification(String downpaymentIdentification) {
        this.downpaymentIdentification = downpaymentIdentification;
    }

    public String getDownpaymentIdentification() {
        return downpaymentIdentification;
    }
}
