package org.coursework.api;

import org.coursework.base.BaseAPITest;
import org.coursework.api.model.project.Project;
import org.coursework.api.model.project.ProjectExtended;
import org.coursework.api.model.user.User;
import org.coursework.utils.FieldsHelper;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.coursework.api.procedures.ProjectProcedures.*;
import static org.coursework.api.procedures.UserProcedures.*;
import static org.coursework.utils.TestData.*;

public class ProjectTest extends BaseAPITest {
    User user;

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        user = createUser(generateDefaultUserData(), ADMIN);
    }

    @Test(groups = {"CRUD_project_API", "API", "smoke_API"})
    public void projectFlow() {
        Project project = createProject(generateProjectWithOwnerData(user.getId()), user);

        ProjectExtended projectInfo = getProjectById(project.getId(), user);
        assertItemField(project.getName(), projectInfo.getName(), FieldsHelper.getProjectNameField());

        boolean isProjectRemoved = removeProjectById(project.getId(), user);
        itemRemovingRequestIsSuccessful(isProjectRemoved);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        removeUserById(user.getId(), ADMIN);
    }
}
