package org.coursework.page.logged_in;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.base.BasePage;
import org.coursework.page.logged_in.project.modal_windows.CreateProjectModalWindow;

public class DashboardPage extends BasePage {
    private SelenideElement search = Selenide.$("input#form-search");
    private SelenideElement newProjectButton = Selenide.$x("//section//a[@href='/project/create']");

    private CreateProjectModalWindow createProjectModalWindow = new CreateProjectModalWindow();

    public DashboardPage() {
        super("/dashboard");
    }

    @Step
    public void openCreateProjectWindow(String name) {
        newProjectButton.click();
        createProjectModalWindow.createProjectOnlyRequiredFields(name);
    }

    @Override
    protected SelenideElement readyElement() {
        return search;
    }
}
