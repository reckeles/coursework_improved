package org.coursework.base;

import com.codeborne.selenide.SelenideElement;
import org.coursework.utils.Wait;
import org.openqa.selenium.WebDriver;
import org.coursework.Session;

import java.time.Instant;

public abstract class BasePage {

    public abstract void openPage();

    public void confirmPageIsLoaded() {
        if (!isPageLoaded(5))
            throw new RuntimeException("Could not confirm that page is loaded: "
                    + getClass().getSimpleName());
    }

    protected abstract SelenideElement readyElement();

//    protected WebDriver wd() {
//        return Session.get().getWebDriver();
//    }

    private Boolean customConfirm() {
        return null;
    }

    private boolean isPageLoaded(int timeoutSec) {
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
}
