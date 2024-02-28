package org.coursework.base;

import org.coursework.api.model.user.User;
import org.coursework.config.EnvConfig;

import static org.coursework.config.EnvConfig.*;

public abstract class BaseAPITest {
    protected final User ADMIN = User.builder()
            .username(getEnvProperties().adminUsername)
            .password(getEnvProperties().adminPassword)
            .build();
}
