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
    private ThreadLocal<User> user = new ThreadLocal<>();
    private ThreadLocal<Project> project = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void before() {
        user.set(createUser(generateDefaultUserData(), ADMIN));
        var currentUser = user.get();

        project.set(createProject(generateProjectWithOwnerData(currentUser.getId()), currentUser));
    }

    @Test(groups = {"CRUD_task_API", "API", "smoke_API"})
    public void taskFlow() {
        var currentUser = user.get();
        var currentProject = project.get();

        Task task = createTask(generateDefaultTaskData(currentProject.getId()), currentUser);

        TaskExtended taskInfo = getTaskById(task.getId(), currentUser);
        assertItemField(task.getTitle(), taskInfo.getTitle(), getTaskTitleField());

        boolean isTaskRemoved = removeTaskById(task.getId(), currentUser);
        itemRemovingRequestIsSuccessful(isTaskRemoved);
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        var currentProject = project.get();
        var currentUser = user.get();

        removeProjectById(currentProject.getId(), currentUser);
        project.remove();

        removeUserById(currentUser.getId(), ADMIN);
        user.remove();
    }
}
