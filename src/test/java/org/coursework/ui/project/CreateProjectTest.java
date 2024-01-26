package org.coursework.ui.project;

import org.coursework.base.BaseGUITest;
import org.coursework.api.model.user.User;
import org.coursework.page.logged_in.project.CreateProjectModalWindow;
import org.coursework.page.logged_in.DashboardPage;
import org.coursework.page.logged_in.project.ProjectPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.coursework.api.procedures.ProjectProcedures.removeProjectById;
import static org.coursework.api.procedures.UserProcedures.createUser;
import static org.coursework.api.procedures.UserProcedures.removeUserById;
import static org.coursework.utils.TestData.generateDefaultUserData;
import static org.coursework.utils.TestData.getRandomStr;

public class CreateProjectTest extends BaseGUITest {
    private ThreadLocal<User> user = new ThreadLocal<>();
    private ThreadLocal<Integer> projectId = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void before() {
        user.set(createUser(generateDefaultUserData(), ADMIN));
        var currentUser = user.get();

        setWebDriver();

        login(currentUser.getUsername(), currentUser.getPassword());
    }

    @Test(groups = {"CRUD_project_UI", "UI", "smoke_UI"})
    public void createProject() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.openPage();
        CreateProjectModalWindow createProjectModalWindow = dashboardPage.openCreateProjectWindow();
        ProjectPage projectPage = createProjectModalWindow.createProjectOnlyRequiredFields(getRandomStr());
        projectPage.confirmPageIsLoaded();
        projectPage.assertPageUrlIsRight();
        projectId.set(projectPage.getProjectId());
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        var currentUser = user.get();

        closeWebDriver();

        removeProjectById(projectId.get(), currentUser);

        removeUserById(currentUser.getId(), ADMIN);
        user.remove();
    }
}
