package org.coursework.ui.task;

import org.coursework.base.BaseGUITest;
import org.coursework.api.model.project.Project;
import org.coursework.api.model.user.User;
import org.coursework.page.logged_in.board.BoardPage;
import org.coursework.page.logged_in.board.CreateTaskModalWindow;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.coursework.api.procedures.ProjectProcedures.createProject;
import static org.coursework.api.procedures.ProjectProcedures.removeProjectById;
import static org.coursework.api.procedures.UserProcedures.createUser;
import static org.coursework.api.procedures.UserProcedures.removeUserById;
import static org.coursework.utils.TestData.*;

public class CreateTaskTest extends BaseGUITest {
    private ThreadLocal<User> user = new ThreadLocal<>();
    private ThreadLocal<Project> project = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void before() {
        user.set(createUser(generateDefaultUserData(), ADMIN));
        var currentUser = user.get();

        project.set(createProject(generateProjectWithOwnerData(currentUser.getId()), currentUser));

        setWebDriver();

        login(currentUser.getUsername(), currentUser.getPassword());
    }

    @Test(groups = {"CRUD_task_UI", "UI", "smoke_UI", "single"})
    public void createTask() {
        var currentProject = project.get();

        BoardPage boardPage = new BoardPage();
        boardPage.setProjectId(currentProject.getId());
        boardPage.openPage();
        String taskName = getRandomStr();
        CreateTaskModalWindow createTaskModalWindow = boardPage.openAddTaskFormFromBacklog();
        boardPage = createTaskModalWindow.createTaskOnlyRequiredFields(taskName, boardPage);
        boardPage.setTasksNumberInBacklog(boardPage.getTasksNumberInBacklog() + 1);
        boardPage.addedTaskIsVisible();
        boardPage.assertLastTaskNameIsRightBacklogColumn(taskName);
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        var currentProject = project.get();
        var currentUser = user.get();

        closeWebDriver();

        removeProjectById(currentProject.getId(), currentUser);
        project.remove();

        removeUserById(currentUser.getId(), ADMIN);
        user.remove();
    }
}

