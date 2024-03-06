package org.coursework.page.logged_in.task.modal_windows;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.page.common.ModalWindow;
import org.coursework.page.logged_in.task.TaskPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class CloseTaskModalWindow extends ModalWindow {
    private SelenideElement submitButton = Selenide.$x("//div[@id='modal-overlay']//button[@id='modal-confirm-button']");

    @Step
    public void confirmCloseAction() {
        submitButton.shouldBe(visible);
        submitButton.click();
    }
}
