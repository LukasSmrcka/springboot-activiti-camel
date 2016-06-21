package io.fourfinanceit.activiti;


import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.boot.autoconfigure.security.FallbackWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@ComponentScan
@EnableJms
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class , SecurityFilterAutoConfiguration.class,org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class , FallbackWebSecurityAutoConfiguration.class})
public class ActivitiApplication extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter{

    public static void main(String... args) {
       SpringApplication.run(ActivitiApplication.class, args);
    }

}
