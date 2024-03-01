package org.coursework.base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.coursework.config.EnvConfig;
import org.coursework.utils.Wait;

import java.time.Instant;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public abstract class BasePage {
    private int timeout = 5;
    private final String pageUrl;

    public BasePage(String relativeUrl) {
        this.pageUrl = EnvConfig.getEnvProperties().baseUrl + relativeUrl;
    }

    public BasePage(String relativeUrl, Integer itemId) {
        Wait.sleep(2 * 1000); //TODO refactor
        if (itemId == null) {
            itemId = getItemIdFromUrl();
        }
        this.pageUrl = EnvConfig.getEnvProperties().baseUrl + relativeUrl + itemId;
    }

    @Step
    public void open() {
        Selenide.open(pageUrl);
        confirmPageIsLoaded();
    }

    @Step
    public void confirmPageIsLoaded() {
        if (!isPageLoaded(timeout)) {
            throw new RuntimeException("Could not confirm that page is loaded: "
                    + getClass().getSimpleName());
        }
    }

    protected void findElementByText(String tag, String text) { //TODO - String tag to enum??
        String sel = String.format("//%s[contains(text(), '%s')]", tag, text);
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

    protected Integer getItemIdFromUrl() {
        String url = WebDriverRunner.url();
        String[] splitURL = url.split("/");
        return Integer.parseInt(splitURL[splitURL.length - 1]);
    }
}
