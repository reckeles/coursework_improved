package org.coursework;

import org.coursework.base.BaseWebDriverCreator;
import org.coursework.config.EnvConfig;
import org.coursework.webDriverInitialization.GridWebDriverCreator;
import org.coursework.webDriverInitialization.LocalWebDriverCreator;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class Session {

    private static final ThreadLocal<Session> INSTANCE = new ThreadLocal<>();

    private BaseWebDriverCreator webDriverCreator;
    private WebDriver webDriver;

    public static Session get() {
        if (INSTANCE.get() == null) {
            INSTANCE.set(new Session());
        }
        return INSTANCE.get();
    }

    public WebDriver getWebDriver() {
        if (webDriver == null) {
            setupWebDriver();
        }
        return webDriver;
    }

    private void setupWebDriver() {
        if ("local".equalsIgnoreCase(EnvConfig.getEnvProperties().envName)) {
            webDriverCreator = new LocalWebDriverCreator();
        } else if ("CI".equalsIgnoreCase(EnvConfig.getEnvProperties().envName)) {
            webDriverCreator = new GridWebDriverCreator();
        } else {
            throw new RuntimeException("Unsupported env: " + EnvConfig.getEnvProperties().envName);
        }

        webDriver = webDriverCreator.createWebDriver();
        webDriver.manage().window().maximize();
    }

    public void close() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    private Session() {
        Runtime.getRuntime().addShutdownHook(new Thread(Session.this::close));
    }
}
