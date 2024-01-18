package org.coursework.webDriverInitialization;

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

public class CIWebDriver extends BaseWebDriver {
    @Override
    public WebDriver createDriver() {
        String host;
        String port;
        DesiredCapabilities caps = new DesiredCapabilities();
        if ("chrome".equalsIgnoreCase(WEB_BROWSER)) {
            host = EnvConfig.SELENIUM_CI_CHROME_HOST.value;
            port = EnvConfig.SELENIUM_CI_CHROME_PORT.value;
            caps.setCapability(ChromeOptions.CAPABILITY, this.getCommonChromeOptions());
            caps.setCapability(CapabilityType.BROWSER_NAME, Browser.CHROME.browserName());
            caps.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);

        } else if ("firefox".equalsIgnoreCase(WEB_BROWSER)) {
            host = EnvConfig.SELENIUM_CI_FIREFOX_HOST.value;
            port = EnvConfig.SELENIUM_CI_FIREFOX_PORT.value;
            caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, this.getCommonFirefoxOptions());
            caps.setCapability(CapabilityType.BROWSER_NAME, Browser.FIREFOX.browserName());
            caps.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        } else
            throw new RuntimeException("Unsupported browser: " + WEB_BROWSER);

        String hubUrl = "http://" + host + ":" + port + "/wd/hub";

        try {
            URL url = new URL(hubUrl);
            return new RemoteWebDriver(url, caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL is not valid: " + hubUrl, e);
        }
    }
}
