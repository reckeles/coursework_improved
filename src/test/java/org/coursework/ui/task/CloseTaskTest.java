package org.coursework.ui.task;

import org.coursework.base.BaseGUITest;
import org.coursework.api.model.project.Project;
import org.coursework.api.model.task.Task;
import org.coursework.api.model.user.User;
import org.coursework.page.logged_in.task.TaskPage;
import org.coursework.page.logged_in.task.CloseTaskModalWindow;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.coursework.api.procedures.ProjectProcedures.createProject;
import static org.coursework.api.procedures.ProjectProcedures.removeProjectById;
import static org.coursework.api.procedures.TaskProcedures.createTask;
import static org.coursework.api.procedures.UserProcedures.createUser;
import static org.coursework.api.procedures.UserProcedures.removeUserById;
import static org.coursework.utils.TestData.*;

public class CloseTaskTest extends BaseGUITest {
    User user;
    Project project;
    Task task;

    @BeforeMethod(alwaysRun = true)
    public void before() {
        user = createUser(generateDefaultUserData(), ADMIN);
        project = createProject(generateProjectWithOwnerData(user.getId()), user);
        task = createTask(generateDefaultTaskData(project.getId()), user);

        setWebDriver();
        login(user.getUsername(), user.getPassword());
    }

    @Test(groups = {"CRUD_task_UI", "UI", "smoke_UI"})
    public void closeTask() {
        TaskPage taskPage = new TaskPage();
        taskPage.setTaskId(task.getId());
        taskPage.openPage();
        CloseTaskModalWindow closeTaskModalWindow = taskPage.openCloseTaskModalWindow();
        taskPage = closeTaskModalWindow.confirmCloseAction();
        taskPage.assertTaskIsClosed();
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        closeWebDriver();

        removeProjectById(project.getId(), user);
        project = null;
        removeUserById(user.getId(), ADMIN);
        user = null;
    }
}
