package org.coursework.base;

import com.codeborne.selenide.SelenideElement;
import org.coursework.utils.Wait;
import org.openqa.selenium.WebDriver;
import org.coursework.Session;

import java.time.Instant;

abstract public class BasePage {
    abstract public void openPage();

    public boolean isPageLoaded(int timeoutSec) {
        Boolean customConfirm = customConfirm();
        if (customConfirm != null) {
            return customConfirm;
        }
        boolean result = false;

        long timeout = Instant.now().getEpochSecond() + timeoutSec;
        while (timeout > Instant.now().getEpochSecond()) {
            result = readyElement().exists();
            if (result)
                break;
            Wait.sleep(500);
        }
        return result;
    }

    public void confirmPageIsLoaded() {
        if (!isPageLoaded(5))
            throw new RuntimeException("Could not confirm that page is loaded: "
                    + getClass().getSimpleName());
    }

    abstract protected SelenideElement readyElement();

    protected WebDriver wd() {
        return Session.get().getWebDriver();
    }

    protected Boolean customConfirm() {
        return null;
    }
}
