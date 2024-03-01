package org.coursework.page.logged_in;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.base.BasePage;
import org.coursework.page.logged_in.project.modal_windows.CreateProjectModalWindow;

import static com.codeborne.selenide.Selenide.page;

public class DashboardPage extends BasePage {
    private SelenideElement search = Selenide.$("input#form-search");
    private SelenideElement newProjectButton = Selenide.$x("//section//a[@href='/project/create']");

    public DashboardPage() {
        super("/dashboard");
    }

    @Step("User is logged in successfully")
    public void searchVisible() {
        confirmPageIsLoaded();
    }

    @Step
    public CreateProjectModalWindow openCreateProjectWindow() {
        newProjectButton.click();
        return page(CreateProjectModalWindow.class);
    }

    @Override
    protected SelenideElement readyElement() {
        return search;
    }
}
