package org.coursework.base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.config.EnvConfig;
import org.coursework.utils.Wait;

import java.time.Instant;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public abstract class BasePage {

    private final String pageUrl;

    public BasePage(String relativeUrl) {
        this.pageUrl = EnvConfig.getEnvProperties().baseUrl + relativeUrl;
    }

    @Step
    public void open() {
        Selenide.open(pageUrl);
        confirmPageIsLoaded();
    }

    @Step
    public void confirmPageIsLoaded(){
        if (!isPageLoaded(5)) {
            throw new RuntimeException("Could not confirm that page is loaded: "
                    + getClass().getSimpleName());
        }
    }

    protected void findElementByText(String text){
        String sel = String.format("//*[text()='%s']", text);
        $x(sel).shouldBe(visible);
    }

    protected abstract SelenideElement readyElement();

    protected boolean isPageLoaded(int timeoutSec) {
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
