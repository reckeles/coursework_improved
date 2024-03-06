package org.coursework.base;

import com.codeborne.selenide.WebDriverRunner;
import org.coursework.config.EnvConfig;
import org.coursework.Session;
import org.coursework.api.model.user.User;
import org.coursework.page.logged_in.DashboardPage;
import org.coursework.page.auth.LoginPage;
import org.openqa.selenium.WebDriver;

public abstract class BaseGUITest {

    protected final User ADMIN = User.builder()
            .username(EnvConfig.getEnvProperties().adminUsername)
            .password(EnvConfig.getEnvProperties().adminPassword)
            .build();

    protected void setWebDriver() {
        Session.get().getWebDriver().get(EnvConfig.getEnvProperties().baseUrl);
        WebDriverRunner.setWebDriver(wd());
    }

    protected void closeWebDriver() {
        Session.get().close();
    }

    protected void login(String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.confirmPageIsLoaded();
    }

    private WebDriver wd() {
        return Session.get().getWebDriver();
    }
}
