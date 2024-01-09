package org.coursework.page.logged_in.project;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.page.common.ModalWindow;

import static com.codeborne.selenide.Selenide.page;

public class CreateProjectModalWindow extends ModalWindow {
    private SelenideElement nameInput = Selenide.$("input#form-name");
    private SelenideElement idInput = Selenide.$("input#form-identifier");
    private SelenideElement perSwimlaneTaskLimitsCheckbox = Selenide.$x("//input[@name='per_swimlane_task_limits']");
    private SelenideElement taskLimitInput = Selenide.$("input#form-task_limit");

    @Step
    public ProjectPage createProjectOnlyRequiredFields(String name) {
        nameInput.sendKeys(name);
        submitButton.click();
        return page(ProjectPage.class);
    }

    @Step
    public void createProjectAllFields(String name, String id, int taskLimit) {
        nameInput.sendKeys(name);
        idInput.sendKeys(id);
        perSwimlaneTaskLimitsCheckbox.click();
        taskLimitInput.sendKeys(String.valueOf(taskLimit));
        submitButton.click();
    }
}
