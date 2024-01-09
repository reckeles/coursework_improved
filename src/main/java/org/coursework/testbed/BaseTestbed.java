package org.coursework.testbed;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

import static org.coursework.utils.PropertyUtil.getProperty;

abstract public class BaseTestbed {
    private final boolean HEADLESS_FLAG = Boolean.valueOf(getProperty("headless", "true"));
    protected final String WEB_BROWSER = getProperty("browser", "chrome");

    protected final ChromeOptions getCommonChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-default-apps");
        options.addArguments("disable-extensions");
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("browser.show_home_button", false);
        options.addArguments("start-maximized");
        if (HEADLESS_FLAG) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
        }
        return options;
    }

    protected final FirefoxOptions getCommonFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        //TODO switch to capabilities
        options.addArguments("disable-default-apps");
        options.addArguments("disable-extensions");
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("browser.show_home_button", false);
        options.addArguments("start-maximized");
        if (HEADLESS_FLAG) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("start-maximized");
        }
        return options;
    }

    abstract public WebDriver createDriver();
}
