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
//        try {
            user.set(createUser(generateDefaultUserData(), ADMIN));
            var currentUser = user.get();
            System.out.println(Thread.currentThread().getId() + "[debug]: user ok");

            project.set(createProject(generateProjectWithOwnerData(currentUser.getId()), currentUser));
            var currentProject = project.get();
            System.out.println(Thread.currentThread().getId() + "[debug]: project ok");

            task.set(createTask(generateDefaultTaskData(currentProject.getId()), currentUser));
            System.out.println(Thread.currentThread().getId() + "[debug]: task ok");

            System.out.println("USER " + currentUser.getUsername() + "PROJECT " + currentProject.getId() + "TASK " + task.get().getTitle());

            setWebDriver();

            login(currentUser.getUsername(), currentUser.getPassword());
            System.out.println(Thread.currentThread().getId() + "[debug]: ok");
//        } catch (Exception e) {
//            System.out.println(Thread.currentThread().getId() + "[debug]: exception occurred: " + e.getMessage());
//            throw e;
//        }
    }

    @Test(groups = {"CRUD_task_UI", "UI", "smoke_UI", "DEBUG"})
    public void addCommentViaModalWindow() {
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

    @Test(groups = {"CRUD_task_UI", "UI", "DEBUG"})
    public void addCommentViaFormOnTaskPage() {
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
