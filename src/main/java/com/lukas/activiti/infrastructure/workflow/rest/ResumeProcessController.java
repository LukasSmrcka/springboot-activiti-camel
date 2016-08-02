package com.lukas.activiti.infrastructure.workflow.rest;

import com.lukas.activiti.infrastructure.workflow.serviceleyer.ResumeProcessCommand;
import com.lukas.activiti.infrastructure.workflow.serviceleyer.ResumeProcessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

@RestController
public class ResumeProcessController {

    @Autowired
    private ResumeProcessHandler resumeProcessHandler;

    @RequestMapping(value = "/loan-applications/resume-process", method = RequestMethod.POST)
    public String simulatePayment(@RequestBody ResumeProcessCommand resumeProcessCommand) {

        resumeProcessHandler.execute(resumeProcessCommand);

        return MessageFormat.format("Process with id {0} was resumed by message {1}.",
                             resumeProcessCommand.getProcessId(),
                             resumeProcessCommand.getMessageName());

    }
}
