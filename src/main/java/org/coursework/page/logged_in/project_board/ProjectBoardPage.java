package org.coursework.page.logged_in.project_board;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.base.BasePage;
import org.coursework.page.logged_in.project_board.blocks.TaskPreviewBlock;
import org.coursework.page.logged_in.project_board.modal_windows.CreateTaskModalWindow;
import org.coursework.utils.HTMLTags;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class ProjectBoardPage extends BasePage {
    private SelenideElement boardTabelHeader = $("tr[class^='board-swimlane-columns']");

    private List<SelenideElement> columns = Selenide.$$("tr[class^='board-swimlane-columns'] th");
    private List<SelenideElement> columnsNames = Selenide.$$x("//tr[contains(@class, 'board-swimlane-columns')]/th//span[contains(@class, 'board-column-title')]/span/a");

    private String addTaskIconById = "th[data-column-id='%s'] a.js-modal-large i";
    private String taskCardByColumn = "//td[contains(@class, '%s')]/div/div[contains(@class, 'task-board')]";

    private CreateTaskModalWindow createTaskModalWindow = new CreateTaskModalWindow();
    private HashMap<String, String> boardColumnsIdToName = new HashMap<>();

    public ProjectBoardPage(Integer projectId) {
        super("/board/" + projectId);
        setIdToNameMap();
    }

    @Override
    public void open() {
        super.open();
        setIdToNameMap();
    }

    @Step
    public void addTask(String columnName, String title) {
        String columnId = boardColumnsIdToName.get(columnName);
        String sel = String.format(addTaskIconById, columnId);
        $(sel).click();
        createTaskModalWindow.createTaskOnlyRequiredFields(title);
    }

    @Step
    public void addedTaskIsVisible(String taskName) {
        findElementByText(HTMLTags.A, taskName);
    }

    public TaskPreviewBlock getLastTaskInTheColumn(String columnName) {
        List<TaskPreviewBlock> tasks = getTasksInColumn(columnName);
        return tasks.get(tasks.size() - 1);
    }

    @Override
    protected SelenideElement readyElement() {
        return boardTabelHeader;
    }

    private List<TaskPreviewBlock> getTasksInColumn(String columnName) {
        String columnId = boardColumnsIdToName.get(columnName);
        String sel = String.format(taskCardByColumn, columnId);
        List<SelenideElement> elements = Selenide.$$x(sel);
        return elements.stream()
                .map(TaskPreviewBlock::new)
                .collect(Collectors.toList());
    }

    private void setIdToNameMap() {
        if (columns.size() != columnsNames.size()) {
            throw new RuntimeException("Columns' number: " + columns.size() + " is not the same as columns name's number:" + columnsNames.size());
        } else {
            for (int i = 0; i < columns.size(); i++) {
                boardColumnsIdToName.put(columnsNames.get(i).text(), columns.get(i).getAttribute("data-column-id"));
            }
        }
    }

    public enum BoardDefaultStatus {
        BACKLOG("Backlog"),
        READY("Ready"),
        WIP("Work in progress"),
        DONE("Done");

        private String status;

        BoardDefaultStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }
}
