package com.lukas.activiti.loanapplication.rest;


import com.lukas.activiti.infrastructure.communication.EntityStatePublisher;
import com.lukas.activiti.loanapplication.domain.LoanApplication;
import com.lukas.activiti.loanapplication.domain.LoanApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoPersistAndNotifyController {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    EntityStatePublisher publisher;

    @RequestMapping("/persist-and-notify")
    @Transactional
    public String persistAndNotify() {

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setFirstName("Bob");
        loanApplication.setLastName("Rambo");
        loanApplicationRepository.save(loanApplication);

        publisher.publishAfterTransactionCommit(loanApplication);

        return "Persisted and notified";
    }
}
