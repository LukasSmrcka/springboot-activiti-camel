package io.fourfinanceit.activiti;

import org.activiti.bpmn.model.MessageEventDefinition;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivitiConfiguration {
    @Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final RuntimeService runtimeService,
                                  final TaskService taskService) {

        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                //System.out.println("Number of process definitions : "
                //        + repositoryService.createProcessDefinitionQuery().count());
                //System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
                //runtimeService.startProcessInstanceByKey("oneTaskProcess");
                ////runtimeService.
                //System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
                //MessageEventDefinition eventDefinition = new MessageEventDefinition();
                //eventDefinition.setMessageRef("text message");
               // eventDefinition.

                //System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                //System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                //System.out.println("");
                //runtimeService.startProcessInstanceByKey("processx");
                //System.out.println("");
                //System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                //System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
            }
        };

    }
}