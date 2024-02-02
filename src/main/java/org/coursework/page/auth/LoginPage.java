package org.coursework.page.auth;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.config.EnvConfig;
import org.coursework.config.TextConfig;
import org.coursework.base.BasePage;
import org.coursework.page.logged_in.DashboardPage;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends BasePage {
    private SelenideElement usernameInput = Selenide.$("input#form-username");
    private SelenideElement passwordInput = Selenide.$("input#form-password");
    private SelenideElement submitButton = Selenide.$x("//button[@type='submit']");

    private SelenideElement badCredentialsAlert = Selenide.$("p.alert.alert-error");

    @Step("Attempt of login")
    public DashboardPage login(String name, String password) {
        usernameInput.sendKeys(name);
        passwordInput.sendKeys(password);
        submitButton.click();
        return page(DashboardPage.class);
    }

    @Step
    public void loginWithoutPassword(String name) {
        usernameInput.sendKeys(name);
        submitButton.click();
    }

    @Step
    public void loginWithoutUsername(String password) {
        passwordInput.sendKeys(password);
        submitButton.click();
    }

    @Step
    public void usernameIsRequired() {
        usernameInput.shouldHave(attribute("required"));
    }

    @Step
    public void passwordIsRequired() {
        passwordInput.shouldHave(attribute("required"));
    }

    @Step
    public void assertBadCredsAlertIsPresent() {
        System.out.println("DEBUG "+TextConfig.getTextProperties().loginPageBadCreds);
        System.out.println("DEBUG "+TextConfig.getTextProperties().taskStatusClosedLabel);
        Assert.assertEquals(badCredentialsAlert.getText(), TextConfig.getTextProperties().loginPageBadCreds);
    }

    @Override
    public void openPage() {
        open(EnvConfig.getEnvProperties().baseUrl + "/login");
    }

    @Override
    protected SelenideElement readyElement() {
        return usernameInput;
    }
}
