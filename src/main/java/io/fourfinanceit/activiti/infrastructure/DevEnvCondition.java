package io.fourfinanceit.activiti.infrastructure;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DevEnvCondition implements Condition {

    public static final String DEV_PROFILE_NAME = "dev";
    public static final String ENVIRONMENT_SYSTEM_PROPERTY = "APP_ENV";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return DEV_PROFILE_NAME.equals(context.getEnvironment().getProperty(ENVIRONMENT_SYSTEM_PROPERTY));
    }
}
