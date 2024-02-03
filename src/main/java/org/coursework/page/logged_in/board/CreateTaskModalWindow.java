package org.coursework.page.logged_in.board;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.coursework.page.common.ModalWindow;

public class CreateTaskModalWindow extends ModalWindow {
    private SelenideElement titleInput = Selenide.$("input#form-title");

    public BoardPage createTaskOnlyRequiredFields(String title, BoardPage boardPage){
        titleInput.sendKeys(title);
        submitButton.click();
        return boardPage;
    }
}
