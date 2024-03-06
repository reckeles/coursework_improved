package org.coursework.page.logged_in.task;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.base.BasePage;
import org.coursework.config.TextConfig;
import org.coursework.page.logged_in.task.blocks.CommentBlock;
import org.coursework.page.logged_in.task.modal_windows.AddCommentToTaskModalWindow;
import org.coursework.page.logged_in.task.modal_windows.CloseTaskModalWindow;
import org.coursework.utils.HTMLTags;
import org.coursework.utils.Wait;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class TaskPage extends BasePage {
    private String commentSelector = "//div[contains(@id, 'comment-')]";

    private SelenideElement taskSummaryBox = $("section#task-summary");

    private SelenideElement commentsDetails = $x("//details[@class='accordion-section'][6]");

    private SelenideElement closeTaskAction = $x("//a[contains(@href, 'close')]");
    private SelenideElement addCommentAction = $x("//a[contains(@href, '/comment/create')]");

    private SelenideElement statusLabel = $x("//div[@class='task-summary-column'][1]//li[1]/span");

    private SelenideElement textAreaCommentForm = $x("//textarea[@name='comment']");
    private SelenideElement submitButtonCommentForm = $x("//button[@type='submit']");

    private CloseTaskModalWindow closeTaskModalWindow = new CloseTaskModalWindow();
    private AddCommentToTaskModalWindow addCommentToTaskModalWindow = new AddCommentToTaskModalWindow();

    public TaskPage(Integer taskId) {
        super("/task/" + taskId);
    }

    @Step
    public void closeTask() {
        closeTaskAction.click();
        closeTaskModalWindow.confirmCloseAction();

        Wait.sleep(2 * 1000);
        Assert.assertEquals(statusLabel.getText(), TextConfig.getTextProperties().taskStatusClosedLabel, "Task status is not closed.");
    }

    @Step
    public void addCommentWithoutEmailWithModalWindow(String comment) {
        addCommentAction.click();
        addCommentToTaskModalWindow.addCommentWithoutEmailWithModalWindow(comment);
    }

    @Step
    public void addComment(String comment) {
        commentsDetails.click();
        textAreaCommentForm.sendKeys(comment);
        submitButtonCommentForm.click();
    }

    @Step
    public void addedCommentIsVisible(String commentText) {
        findElementByText(HTMLTags.P, commentText);
    }

    public CommentBlock getLastCommentInTheList() {
        List<CommentBlock> comments = getCommentsOnPage();
        return comments.get(comments.size() - 1);
    }

    @Override
    protected SelenideElement readyElement() {
        return taskSummaryBox;
    }

    private List<CommentBlock> getCommentsOnPage() {
        List<SelenideElement> elements = Selenide.$$x(commentSelector);
        return elements.stream()
                .map(CommentBlock::new)
                .collect(Collectors.toList());
    }
}
