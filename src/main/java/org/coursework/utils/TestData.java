package org.coursework.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.coursework.api.model.project.Project;
import org.coursework.api.model.task.Task;
import org.coursework.api.model.user.User;

import java.util.Random;

public class TestData {
    private static final String ADMIN_ROLE = "app-admin";
    private static final String MANAGER_ROLE = "app-manager";
    private static final String USER_ROLE = "app-user";

    public static String getRandomStr() {
        int length = getRandomInt(50);
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public static User generateDefaultUserData() {
//        return new User("newuser" + getRandomInt(), "password_123", "Yuliia", "yuliia@gmail.com", ADMIN_ROLE);
        return User.builder()
                .username("newuser" + getRandomInt())
                .password("password_123")
                .name("Yuliia")
                .email("yuliia@gmail.com")
                .role(ADMIN_ROLE)
                .build();
    }

    public static Project generateDefaultProjectData() {
//        return new Project("project" + getRandomInt());
        return Project.builder()
                .name("project" + getRandomInt())
                .build();
    }

    public static Project generateProjectWithOwnerData(Integer userId) {
//        return new Project("project" + getRandomInt(), userId);
        return Project.builder()
                .name("project" + getRandomInt())
                .owner_id(userId)
                .build();
    }

    public static Task generateDefaultTaskData(Integer projectId) {
        return Task.builder()
                .title("task" + getRandomInt())
                .project_id(projectId)
                .build();
    }

    private static int getRandomInt() {
        Random rand = new Random();
        return rand.nextInt(100000);
    }

    private static int getRandomInt(int range) {
        Random rand = new Random();
        return rand.nextInt(range);
    }
}
