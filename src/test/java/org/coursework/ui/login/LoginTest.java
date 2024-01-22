package org.coursework.ui.login;

import org.coursework.base.BaseGUITest;
import org.coursework.api.model.user.User;
import org.coursework.page.logged_in.DashboardPage;
import org.coursework.page.auth.LoginPage;
import org.testng.annotations.*;

import static org.coursework.api.procedures.UserProcedures.createUser;
import static org.coursework.api.procedures.UserProcedures.removeUserById;
import static org.coursework.utils.TestData.generateDefaultUserData;
import static org.coursework.utils.TestData.getRandomStr;


public class LoginTest extends BaseGUITest {
    private User user;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        user = createUser(generateDefaultUserData(), ADMIN);
        setWebDriver();
    }

    @Test(groups = {"authflow", "UI", "smoke_UI"})
    public void loginValidUser() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = loginPage.login(user.getUsername(), user.getPassword());
        dashboardPage.searchVisible();
    }

    @Test(groups = {"authflow", "UI"})
    public void loginEmptyUsernameField() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithoutPassword(user.getUsername());
        loginPage.usernameIsRequired();
    }

    @Test(groups = {"authflow", "UI"})
    public void loginEmptyPasswordField() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithoutUsername(user.getPassword());
        loginPage.passwordIsRequired();
    }

    @Test(groups = {"authflow", "UI", "smoke_UI"})
    public void loginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(user.getUsername(), getRandomStr());
        loginPage.assertBadCredsAlertIsPresent();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        closeWebDriver();
        removeUserById(user.getId(), ADMIN);
        user = null;
    }
}
