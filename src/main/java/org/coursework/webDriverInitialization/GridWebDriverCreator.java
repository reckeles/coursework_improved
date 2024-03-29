package org.coursework.webDriverInitialization;

import org.coursework.base.BaseWebDriverCreator;
import org.coursework.config.EnvConfig;
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

import static org.coursework.config.BrowserConfig.WEB_BROWSER;

public class GridWebDriverCreator extends BaseWebDriverCreator {
    @Override
    public WebDriver createWebDriver() {
        String host;
        String port;
        DesiredCapabilities caps = new DesiredCapabilities();
        
        if ("chromium".equalsIgnoreCase(WEB_BROWSER.getName())) {
            host = EnvConfig.getEnvProperties().seleniarmChromiumHost;
            port = EnvConfig.getEnvProperties().seleniarmChromiumPort;
            caps.setCapability(ChromeOptions.CAPABILITY, getCommonChromeOptions());
            caps.setCapability(CapabilityType.BROWSER_NAME, Browser.CHROME.browserName());
            caps.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        } else if ("firefox".equalsIgnoreCase(WEB_BROWSER.getName())) {
            host = EnvConfig.getEnvProperties().seleniarmFirefoxHost;
            port = EnvConfig.getEnvProperties().seleniarmFirefoxPort;
            caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, getCommonFirefoxOptions());
            caps.setCapability(CapabilityType.BROWSER_NAME, Browser.FIREFOX.browserName());
            caps.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        } else {
            throw new RuntimeException("Unsupported browser: " + WEB_BROWSER);
        }

//        String gridUrl = "http://" + host + ":" + port; //for standalone grid
        String gridUrl = "http://" + host + ":" + port + "/wd/hub"; //for node grid
        try {
            return new RemoteWebDriver(new URL(gridUrl), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL is not valid: " + gridUrl, e);
        }
    }
}
