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
    private ThreadLocal<User> user = new ThreadLocal<>();
    private ThreadLocal<Project> project = new ThreadLocal<>();
    private ThreadLocal<Task> task = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void before() {
            user.set(createUser(generateDefaultUserData(), ADMIN));
            var currentUser = user.get();

            project.set(createProject(generateProjectWithOwnerData(currentUser.getId()), currentUser));
            var currentProject = project.get();

            task.set(createTask(generateDefaultTaskData(currentProject.getId()), currentUser));

            setWebDriver();

            login(currentUser.getUsername(), currentUser.getPassword());
    }

    @Test(groups = {"CRUD_task_UI", "UI", "smoke_UI"})
    public void addCommentViaModalWindow() {
        System.out.println("THREAD"+Thread.currentThread().getId());
        var currentTask = task.get();
        var currentUser = user.get();

        TaskPage taskPage = new TaskPage();
        taskPage.setTaskId(currentTask.getId());
        taskPage.openPage();
        AddCommentToTaskModalWindow addCommentToTaskModalWindow = taskPage.openAddCommentModalWindow();

        String comment = getRandomStr();
        taskPage = addCommentToTaskModalWindow.addCommentWithoutEmail(comment, taskPage);
        taskPage.setCommentsNumber(taskPage.getCommentsNumber() + 1);
        taskPage.addedCommentIsVisible();

        taskPage.assertCommentCreatorIsSameAsExpected(currentUser.getName());
        taskPage.assertCommentTextIsSameAsExpected(comment);
        //TODO add assertion for dates in comment
    }

    @Test(groups = {"CRUD_task_UI", "UI"})
    public void addCommentViaFormOnTaskPage() {
        System.out.println("THREAD"+Thread.currentThread().getId());
        var currentTask = task.get();
        var currentUser = user.get();

        TaskPage taskPage = new TaskPage();
        taskPage.setTaskId(currentTask.getId());
        taskPage.openPage();

        String comment = getRandomStr();
        taskPage.addComment(comment);
        taskPage.setCommentsNumber(taskPage.getCommentsNumber() + 1);
        taskPage.addedCommentIsVisible();

        taskPage.assertCommentCreatorIsSameAsExpected(currentUser.getName());
        taskPage.assertCommentTextIsSameAsExpected(comment);
        //TODO add assertion for dates in comment
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
