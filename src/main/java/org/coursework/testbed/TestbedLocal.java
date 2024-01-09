package org.coursework.testbed;

import org.coursework.config.EnvConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestbedLocal extends BaseTestbed {
    @Override
    public WebDriver createDriver() {
        WebDriver driver;
        if ("chrome".equalsIgnoreCase(WEB_BROWSER)) {
            driver = new ChromeDriver(this.getCommonChromeOptions());
        } else if ("firefox".equalsIgnoreCase(WEB_BROWSER)) {
            driver = new FirefoxDriver(this.getCommonFirefoxOptions());
        } else {
            throw new RuntimeException("Unsupported browser: " + WEB_BROWSER);
        }
        return driver;
    }
}
