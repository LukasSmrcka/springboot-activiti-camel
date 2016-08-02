package com.lukas.activiti.loanapplication.domain;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LoanApplicationRepository extends CrudRepository<LoanApplication, Long> {

    List<LoanApplication> findByLastName(String lastName);
}
