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
    private User user;
    private Project project;


    @BeforeMethod(alwaysRun = true)
    public void before() {
        user = createUser(generateDefaultUserData(), ADMIN);
        project = createProject(generateProjectWithOwnerData(user.getId()), user);

        setWebDriver();
        login(user.getUsername(), user.getPassword());
    }

    @Test(groups = {"CRUD_task_UI", "UI", "smoke_UI", "single"})
    public void createTask() {
        BoardPage boardPage = new BoardPage();
        boardPage.setProjectId(project.getId());
        boardPage.openPage();
        String taskName = getRandomStr();
        CreateTaskModalWindow createTaskModalWindow = boardPage.openAddTaskFormFromBacklog();
        boardPage = createTaskModalWindow.createTaskOnlyRequiredFields(taskName, boardPage);
        boardPage.setTasksNumberInBacklog(boardPage.getTasksNumberInBacklog()+1);
        boardPage.addedTaskIsVisible();
        boardPage.assertLastTaskNameIsRightBacklogColumn(taskName);
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

