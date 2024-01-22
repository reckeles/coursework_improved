package org.coursework.webDriverInitialization;

import org.coursework.base.AbstractWebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.coursework.config.BrowserConfig.WEB_BROWSER;


public class LocalWebDriverFactory extends AbstractWebDriverFactory {
    @Override
    public WebDriver createWebDriver() {
        WebDriver driver;
        if ("chrome".equalsIgnoreCase(WEB_BROWSER.getName())) {
            driver = new ChromeDriver(this.getCommonChromeOptions());
        } else if ("firefox".equalsIgnoreCase(WEB_BROWSER.getName())) {
            driver = new FirefoxDriver(this.getCommonFirefoxOptions());
        } else {
            throw new RuntimeException("Unsupported browser: " + WEB_BROWSER.getName());
        }
        return driver;
    }
}
