package org.coursework.api;

import org.coursework.base.BaseAPITest;
import org.coursework.api.model.project.Project;
import org.coursework.api.model.project.ProjectExtended;
import org.coursework.api.model.user.User;
import org.coursework.utils.FieldsHelper;
import org.testng.annotations.*;

import static org.coursework.api.procedures.ProjectProcedures.*;
import static org.coursework.api.procedures.UserProcedures.*;
import static org.coursework.utils.TestData.*;

public class ProjectTest extends BaseAPITest {
    private ThreadLocal<User> user = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        user.set(createUser(generateDefaultUserData(), ADMIN));
    }

    @Test(groups = {"CRUD_project_API", "API", "smoke_API"})
    public void projectFlow() {
        var currentUser = user.get();

        Project project = createProject(generateProjectWithOwnerData(currentUser.getId()), currentUser);

        ProjectExtended projectInfo = getProjectById(project.getId(), currentUser);
        assertItemField(project.getName(), projectInfo.getName(), FieldsHelper.getProjectNameField());

        boolean isProjectRemoved = removeProjectById(project.getId(), currentUser);
        itemRemovingRequestIsSuccessful(isProjectRemoved);
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        var currentUser = user.get();

        removeUserById(currentUser.getId(), ADMIN);
        user.remove();
    }
}
