package org.coursework.testbed;

import org.coursework.config.EnvConfig;
import org.coursework.utils.PropertyUtil;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.coursework.utils.PropertyUtil.getProperty;

public class TestbedGrid extends BaseTestbed {
    @Override
    public WebDriver createDriver() {
        String gridHost;
        String gridPort;
        DesiredCapabilities caps = new DesiredCapabilities();
        if ("chrome".equalsIgnoreCase(WEB_BROWSER)) {
            gridHost = EnvConfig.SELENIUM_GRID_CHROME_HOST.value;
            gridPort = EnvConfig.SELENIUM_GRID_CHROME_PORT.value;
            caps.setCapability(ChromeOptions.CAPABILITY, this.getCommonChromeOptions());
            caps.setCapability(CapabilityType.BROWSER_NAME, Browser.CHROME.browserName());
            caps.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);

        } else if ("firefox".equalsIgnoreCase(WEB_BROWSER)) {
            gridHost = EnvConfig.SELENIUM_GRID_FIREFOX_HOST.value;
            gridPort = EnvConfig.SELENIUM_GRID_FIREFOX_PORT.value;
            caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, this.getCommonFirefoxOptions());
            caps.setCapability(CapabilityType.BROWSER_NAME, Browser.FIREFOX.browserName());
            caps.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        } else
            throw new RuntimeException("Unsupported browser: " + WEB_BROWSER);

        String gridUrl = "http://" + gridHost + ":" + gridPort + "/wd/hub";

        try {
            URL url = new URL(gridUrl);
            return new RemoteWebDriver(url, caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL is not valid: " + gridUrl, e);
        }
    }
}
