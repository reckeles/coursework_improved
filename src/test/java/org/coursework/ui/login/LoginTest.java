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
    private ThreadLocal<User> user = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        user.set(createUser(generateDefaultUserData(), ADMIN));

        setWebDriver();
    }

    @Test(groups = {"authflow", "UI", "smoke_UI"})
    public void loginValidUser() {
        var currentUser = user.get();

        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = loginPage.login(currentUser.getUsername(), currentUser.getPassword());
        dashboardPage.searchVisible();
    }

    @Test(groups = {"authflow", "UI"})
    public void loginEmptyUsernameField() {
        var currentUser = user.get();

        LoginPage loginPage = new LoginPage();
        loginPage.loginWithoutPassword(currentUser.getUsername());
        loginPage.usernameIsRequired();
    }

    @Test(groups = {"authflow", "UI"})
    public void loginEmptyPasswordField() {
        var currentUser = user.get();

        LoginPage loginPage = new LoginPage();
        loginPage.loginWithoutUsername(currentUser.getPassword());
        loginPage.passwordIsRequired();
    }

    @Test(groups = {"authflow", "UI", "smoke_UI"})
    public void loginWithInvalidPassword() {
        var currentUser = user.get();

        LoginPage loginPage = new LoginPage();
        loginPage.login(currentUser.getUsername(), getRandomStr());
        loginPage.assertBadCredsAlertIsPresent();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        var currentUser = user.get();

        closeWebDriver();

        removeUserById(currentUser.getId(), ADMIN);
        user.remove();
    }
}
