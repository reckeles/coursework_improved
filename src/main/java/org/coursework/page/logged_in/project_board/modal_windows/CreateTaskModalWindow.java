package org.coursework.page.logged_in.project_board.modal_windows;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.coursework.page.common.ModalWindow;
import org.coursework.page.logged_in.project_board.ProjectBoardPage;

public class CreateTaskModalWindow extends ModalWindow {
    private SelenideElement titleInput = Selenide.$("input#form-title");

    public void createTaskOnlyRequiredFields(String title) {
        titleInput.sendKeys(title);
        submitButton.click();
    }
}
