package org.coursework.page.logged_in;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.config.EnvConfig;
import org.coursework.page.common.LoggedInPage;
import org.coursework.page.logged_in.project.CreateProjectModalWindow;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class DashboardPage extends LoggedInPage {
    private SelenideElement search = Selenide.$("input#form-search");
    private SelenideElement newProjectButton = Selenide.$x("//section//a[@href='/project/create']");

    @Step("User is logged in successfully")
    public void searchVisible() {
        confirmPageIsLoaded();
    }

    @Step
    public CreateProjectModalWindow openCreateProjectWindow() {
        newProjectButton.click();
        return page(CreateProjectModalWindow.class);
    }

    @Step
    @Override
    public void openPage() {
        open(EnvConfig.getBaseURL() + "/dashboard");
    }

    @Override
    protected SelenideElement readyElement() {
        return search;
    }
}
