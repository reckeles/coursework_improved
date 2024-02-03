package org.coursework.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.coursework.api.model.project.Project;
import org.coursework.api.model.task.Task;
import org.coursework.api.model.user.User;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestData {
    public static String getRandomStr() {
        int length = getRandomInt(50);
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public static User generateDefaultUserData() {
        return User.builder()
                .username("newuser" + getRandomInt())
                .password("password_123")
                .name("Yuliia")
                .email("yuliia@gmail.com")
                .role(Roles.ADMIN_ROLE.getRole())
                .build();
    }

    public static Project generateDefaultProjectData() {
        return Project.builder()
                .name("project" + getRandomInt())
                .build();
    }

    public static Project generateProjectWithOwnerData(Integer userId) {
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
        return ThreadLocalRandom.current().nextInt(100000);
    }

    private static int getRandomInt(int range) {
        return ThreadLocalRandom.current().nextInt(range);
    }

    private enum Roles {
        ADMIN_ROLE("app-admin"),
        MANAGER_ROLE("app-manager"),
        USER_ROLE("app-user");

        private String role;

        Roles(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }
}
