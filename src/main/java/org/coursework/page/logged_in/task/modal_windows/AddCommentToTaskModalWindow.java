package org.coursework.page.logged_in.task.modal_windows;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.page.common.ModalWindow;
import org.coursework.page.logged_in.task.TaskPage;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class AddCommentToTaskModalWindow extends ModalWindow {
    private SelenideElement textComment = $x("//div[@id='modal-content']//textarea[@name='comment']");

    @Step
    public void addCommentWithoutEmailWithModalWindow(String comment) {
        textComment.sendKeys(comment);
        submitButton.click();
    }
}
