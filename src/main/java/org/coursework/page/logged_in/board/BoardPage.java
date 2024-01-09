package org.coursework.page.logged_in.board;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.config.EnvConfig;
import org.coursework.page.common.LoggedInFilterPage;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BoardPage extends LoggedInFilterPage {
    private Integer projectId;
    private int tasksNumberInBacklog;
    private String taskPreviewBacklogSelector = "//td[contains(@class, 'board-column')][1]/div/div[contains(@class, 'task-board')]";

    private SelenideElement addTaskIconBacklog = $x("//th[contains(@class, 'board-column-header')][1]//i[contains(@class, 'js-modal-large')]");

    @Step
    public CreateTaskModalWindow openAddTaskFormFromBacklog() {
        addTaskIconBacklog.click();
        return page(CreateTaskModalWindow.class);
    }

    @Step
    public void assertLastTaskNameIsRightBacklogColumn(String expectedName) {
        List<TaskPreviewBlock> tasks = getTasksInBacklog();
        TaskPreviewBlock task = tasks.get(tasks.size() - 1);
        Assert.assertEquals(task.name.text(), expectedName, "Task name is not same as expected.");
    }

    public BoardPage() {
        this.tasksNumberInBacklog = 0;
    }

    public void addedTaskIsVisible() {
        $x(String.format("%s[%s]", taskPreviewBacklogSelector, this.tasksNumberInBacklog)).shouldBe(visible);
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setTasksNumberInBacklog(Integer tasksNumberInBacklog) {
        this.tasksNumberInBacklog = tasksNumberInBacklog;
    }

    public int getTasksNumberInBacklog() {
        return tasksNumberInBacklog;
    }

    @Step
    @Override
    public void openPage() {
        open(EnvConfig.getBaseURL() + "/board/" + projectId);
    }

    @Override
    protected SelenideElement readyElement() {
        return addTaskIconBacklog;
    }

    private List<TaskPreviewBlock> getTasksInBacklog() {
        List<SelenideElement> elements = Selenide.$$x(taskPreviewBacklogSelector);
        return elements.stream()
                .map(TaskPreviewBlock::new)
                .collect(Collectors.toList());
    }
}
