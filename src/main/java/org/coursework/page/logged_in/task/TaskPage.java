package org.coursework.page.logged_in.task;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.config.EnvConfig;
import org.coursework.config.TextConfig;
import org.coursework.page.common.LoggedInFilterPage;
import org.coursework.utils.Wait;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TaskPage extends LoggedInFilterPage {
    private Integer taskId;
    private int commentsNumber;
    private final String commentSelector = "//div[contains(@id, 'comment-')]";

    private SelenideElement taskSummaryBox = $("section#task-summary");

    private SelenideElement commentsDetails = $x("//details[@class='accordion-section'][6]");

    private SelenideElement closeTaskAction = $x("//a[contains(@href, 'close')]");
    private SelenideElement addCommentAction = $x("//a[contains(@href, '/comment/create')]");

    private SelenideElement statusLabel = $x("//div[@class='task-summary-column'][1]//li[1]/span");

    private SelenideElement textAreaCommentForm = $x("//textarea[@name='comment']");
    private SelenideElement submitButtonCommentForm = $x("//button[@type='submit']");

    @Step
    public CloseTaskModalWindow openCloseTaskModalWindow() {
        closeTaskAction.click();
        return page(CloseTaskModalWindow.class);
    }

    @Step
    public AddCommentToTaskModalWindow openAddCommentModalWindow() {
        addCommentAction.click();
        return page(AddCommentToTaskModalWindow.class);
    }

    @Step
    public void addComment(String comment) {
        commentsDetails.click();
        textAreaCommentForm.sendKeys(comment);
        submitButtonCommentForm.click();
    }

    @Step
    public void assertTaskIsClosed() {
        Wait.sleep(3 * 1000);
        Assert.assertEquals(statusLabel.getText(), TextConfig.getTextProperties().taskStatusClosedLabel, "Task status is not closed.");
    }

    private List<CommentBlock> getCommentsOnPage() {
        List<SelenideElement> elements = Selenide.$$x(commentSelector);
        return elements.stream()
                .map(CommentBlock::new)
                .collect(Collectors.toList());
    }

    @Step
    public void assertCommentTextIsSameAsExpected(String expectedCommentText) {
        List<CommentBlock> comments = getCommentsOnPage();
        CommentBlock lastComment = comments.get(comments.size() - 1);
        Assert.assertEquals(lastComment.text.text(), expectedCommentText, "Comment's text is not same as expected.");
    }

    @Step
    public void assertCommentCreatorIsSameAsExpected(String expectedUsername) {
        List<CommentBlock> comments = getCommentsOnPage();
        CommentBlock lastComment = comments.get(comments.size() - 1);
        Assert.assertEquals(lastComment.creator.text(), expectedUsername, "Comment's creator name is not same as expected.");
    }

    public void addedCommentIsVisible(){
        $x(String.format("%s[%s]", commentSelector, commentsNumber)).shouldBe(visible);
    }

    @Override
    protected SelenideElement readyElement() {
        return taskSummaryBox;
    }

    @Step
    @Override
    public void openPage() {
        open(EnvConfig.getBaseURL() + "/task/" + taskId);
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public int getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(int commentsNumber) {
        this.commentsNumber = commentsNumber;
    }
}
