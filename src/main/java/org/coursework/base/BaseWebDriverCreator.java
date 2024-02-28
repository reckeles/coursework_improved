package org.coursework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import static org.coursework.config.BrowserConfig.HEADLESS_FLAG;

public abstract class BaseWebDriverCreator {

    public abstract WebDriver createWebDriver();

    protected final ChromeOptions getCommonChromeOptions() {
        ChromeOptions options = new ChromeOptions()
                .addArguments("--disable-default-apps")
                .addArguments("--disable-extensions")
                .addArguments("--disable-infobars")
                .addArguments("--start-maximized");
        if (HEADLESS_FLAG) {
            options.addArguments("--headless");
        }

        return options;
    }

    protected final FirefoxOptions getCommonFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions()
                .setHeadless(HEADLESS_FLAG)
                .addArguments("--start-maximized");

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("app.update.disabledForTesting",true);
        profile.setPreference("toolkit.cosmeticAnimations.enabled",false);
        options.setProfile(profile);

        return options;
    }
}
