package org.coursework.ui.task;

import org.coursework.base.BaseGUITest;
import org.coursework.api.model.project.Project;
import org.coursework.api.model.user.User;
import org.coursework.page.logged_in.project_board.ProjectBoardPage;
import org.coursework.page.logged_in.project_board.blocks.TaskPreviewBlock;
import org.testng.annotations.*;

import static org.coursework.api.procedures.ProjectProcedures.createProject;
import static org.coursework.api.procedures.ProjectProcedures.removeProjectById;
import static org.coursework.api.procedures.UserProcedures.createUser;
import static org.coursework.api.procedures.UserProcedures.removeUserById;
import static org.coursework.utils.TestData.*;

public class CreateTaskTest extends BaseGUITest {
    private ThreadLocal<User> user = new ThreadLocal<>();
    private ThreadLocal<Project> project = new ThreadLocal<>();
    private ThreadLocal<String> taskName = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void before() {
        user.set(createUser(generateDefaultUserData(), ADMIN));
        var currentUser = user.get();

        project.set(createProject(generateProjectWithOwnerData(currentUser.getId()), currentUser));

        taskName.set(getRandomStr());

        setWebDriver();
        login(currentUser.getUsername(), currentUser.getPassword());
    }

    @DataProvider(name = "columnsNamesList")
    public static Object[][] getBoardColumnsName() {
        return new Object[][]{
                {ProjectBoardPage.BoardDefaultStatus.BACKLOG.getStatus()},
                {ProjectBoardPage.BoardDefaultStatus.READY.getStatus()},
                {ProjectBoardPage.BoardDefaultStatus.WIP.getStatus()},
                {ProjectBoardPage.BoardDefaultStatus.DONE.getStatus()}
        };
    }

    @Test(groups = {"CRUD_task_UI", "UI", "smoke_UI", "single"}, dataProvider = "columnsNamesList")
    public void createTaskInColumn(String columnName) {
        var currentProject = project.get();
        var currentTaskName = taskName.get();
        ProjectBoardPage projectBoardPage = new ProjectBoardPage(currentProject.getId());

        projectBoardPage.open();
        projectBoardPage.addTask(columnName, currentTaskName);
        projectBoardPage.addedTaskIsVisible(currentTaskName);

        TaskPreviewBlock actualTaskPreview = projectBoardPage.getLastTaskInTheColumn(columnName);
        actualTaskPreview.assertTaskName(currentTaskName);
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

