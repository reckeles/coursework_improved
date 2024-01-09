package org.coursework.base;

import org.coursework.api.model.user.User;

import static org.coursework.config.EnvConfig.*;

abstract public class BaseAPITest {
    protected final User ADMIN = User.builder()
            .username(ADMIN_USERNAME.value)
            .password(ADMIN_PASSWORD.value)
            .build();
}
