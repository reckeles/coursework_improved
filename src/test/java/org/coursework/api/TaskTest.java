package org.coursework.api;

import org.coursework.base.BaseAPITest;
import org.coursework.api.model.project.Project;
import org.coursework.api.model.task.Task;
import org.coursework.api.model.task.TaskExtended;
import org.coursework.api.model.user.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.coursework.api.procedures.ProjectProcedures.*;
import static org.coursework.api.procedures.TaskProcedures.*;
import static org.coursework.api.procedures.UserProcedures.*;
import static org.coursework.utils.FieldsHelper.getTaskTitleField;
import static org.coursework.utils.TestData.*;

public class TaskTest extends BaseAPITest {
    Project project;
    User user;

    @BeforeMethod(alwaysRun = true)
    public void before() {
        user = createUser(generateDefaultUserData(), ADMIN);
        project = createProject(generateProjectWithOwnerData(user.getId()), user);
    }

    @Test(groups = {"CRUD_task_API", "API", "smoke_API"})
    public void taskFlow() {
        Task task = createTask(generateDefaultTaskData(project.getId()), user);

        TaskExtended taskInfo = getTaskById(task.getId(), user);
        assertItemField(task.getTitle(), taskInfo.getTitle(), getTaskTitleField());

        boolean isTaskRemoved = removeTaskById(task.getId(), user);
        itemRemovingRequestIsSuccessful(isTaskRemoved);
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        removeProjectById(project.getId(), user);
        removeUserById(user.getId(), ADMIN);
    }
}
