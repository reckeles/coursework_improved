package org.coursework.webDriverInitialization;

import org.coursework.base.BaseWebDriverCreator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.coursework.config.BrowserConfig.WEB_BROWSER;


public class LocalWebDriverCreator extends BaseWebDriverCreator {
    @Override
    public WebDriver createWebDriver() {
        WebDriver driver;
        if ("chrome".equalsIgnoreCase(WEB_BROWSER.getName())) {
            driver = new ChromeDriver(getCommonChromeOptions());
        } else if ("firefox".equalsIgnoreCase(WEB_BROWSER.getName())) {
            driver = new FirefoxDriver(getCommonFirefoxOptions());
        } else {
            throw new RuntimeException("Unsupported browser: " + WEB_BROWSER.getName());
        }
        return driver;
    }
}
