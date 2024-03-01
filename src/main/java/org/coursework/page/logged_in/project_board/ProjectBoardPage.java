package org.coursework.page.logged_in.project_board;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.base.BasePage;
import org.coursework.page.logged_in.project_board.blocks.TaskPreviewBlock;
import org.coursework.page.logged_in.project_board.modal_windows.CreateTaskModalWindow;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class ProjectBoardPage extends BasePage {
    private CreateTaskModalWindow createTaskModalWindow = new CreateTaskModalWindow();

    //selenideelements
    //TODO - enums for columns backlog etc ????
    private String taskCardBacklogSelector = "//td[contains(@class, 'board-column')][1]/div/div[contains(@class, 'task-board')]";
    private SelenideElement addTaskIconBacklog = $x("//th[contains(@class, 'board-column-header')][1]//i[contains(@class, 'js-modal-large')]");

    //constructors
    public ProjectBoardPage(Integer projectId) {
        super("/board/" + projectId);
    }

    //actions with elements
    @Step
    public void addTaskToBacklog(String title) {
        addTaskIconBacklog.click();
        createTaskModalWindow.createTaskOnlyRequiredFields(title);
    }

    //asserts
    @Step
    public void addedTaskIsVisible(String taskName) {
        findElementByText(taskName);
    }

    //helpers and private methods
    //TODO - enums for columns backlog etc ????
    public TaskPreviewBlock getLastTaskInTheBacklogList(){
        List<TaskPreviewBlock> tasks = getTasksInBacklog();
        return tasks.get(tasks.size() - 1);
    }

    @Override
    protected SelenideElement readyElement() {
        return addTaskIconBacklog;
    }

    private List<TaskPreviewBlock> getTasksInBacklog() {
        List<SelenideElement> elements = Selenide.$$x(taskCardBacklogSelector);
        return elements.stream()
                .map(TaskPreviewBlock::new)
                .collect(Collectors.toList());
    }
}
