package org.coursework.ui.task;

import org.coursework.base.BaseGUITest;
import org.coursework.page.logged_in.task.TaskPage;
import org.coursework.page.logged_in.task.AddCommentToTaskModalWindow;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.coursework.api.model.project.Project;
import org.coursework.api.model.task.Task;
import org.coursework.api.model.user.User;

import static org.coursework.api.procedures.ProjectProcedures.createProject;
import static org.coursework.api.procedures.ProjectProcedures.removeProjectById;
import static org.coursework.api.procedures.TaskProcedures.createTask;
import static org.coursework.api.procedures.UserProcedures.createUser;
import static org.coursework.api.procedures.UserProcedures.removeUserById;
import static org.coursework.utils.TestData.*;

public class CommentsTaskTest extends BaseGUITest {
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

    @Test(groups = {"CRUD_task_UI", "UI", "smoke", "regression"})
    public void addCommentViaModalWindow() {
        TaskPage taskPage = new TaskPage();
        taskPage.setTaskId(task.getId());
        taskPage.openPage();
        AddCommentToTaskModalWindow addCommentToTaskModalWindow = taskPage.openAddCommentModalWindow();

        String comment = getRandomStr();
        taskPage = addCommentToTaskModalWindow.addCommentWithoutEmail(comment, taskPage);
        taskPage.setCommentsNumber(taskPage.getCommentsNumber()+1);
        taskPage.addedCommentIsVisible();

        taskPage.assertCommentCreatorIsSameAsExpected(user.getName());
        taskPage.assertCommentTextIsSameAsExpected(comment);
        //TODO add assertion for dates in comment
    }

    @Test(groups = {"CRUD_task_UI", "UI", "regression"})
    public void addCommentViaFormOnTaskPage() {
        TaskPage taskPage = new TaskPage();
        taskPage.setTaskId(task.getId());
        taskPage.openPage();

        String comment = getRandomStr();
        taskPage.addComment(comment);
        taskPage.setCommentsNumber(taskPage.getCommentsNumber()+1);
        taskPage.addedCommentIsVisible();

        taskPage.assertCommentCreatorIsSameAsExpected(user.getName());
        taskPage.assertCommentTextIsSameAsExpected(comment);
        //TODO add assertion for dates in comment
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
