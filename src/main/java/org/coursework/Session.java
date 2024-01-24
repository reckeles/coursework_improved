package org.coursework;

import org.coursework.base.AbstractWebDriverFactory;
import org.coursework.config.EnvConfig;
import org.coursework.webDriverInitialization.GridWebDriverFactory;
import org.coursework.webDriverInitialization.LocalWebDriverFactory;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class Session {

    private static final ThreadLocal<Session> INSTANCE = new ThreadLocal<>();

    private AbstractWebDriverFactory webDriverFactory;
    private WebDriver webDriver;

    public static Session get() {
        if (INSTANCE.get() == null) {
            INSTANCE.set(new Session());
        }
        return INSTANCE.get();
    }

    public WebDriver getWebDriver() {
        if (this.webDriver == null) {
            setupWebDriver();
        }
        return this.webDriver;
    }

    private void setupWebDriver() {
        if ("local".equalsIgnoreCase(EnvConfig.ENV_NAME.value)) {
            this.webDriverFactory = new LocalWebDriverFactory();
        } else if ("CI".equalsIgnoreCase(EnvConfig.ENV_NAME.value)) {
            this.webDriverFactory = new GridWebDriverFactory();
        } else {
            throw new RuntimeException("Unsupported env: " + EnvConfig.ENV_NAME.value);
        }

        this.webDriver = this.webDriverFactory.createWebDriver();
        this.webDriver.manage().window().maximize();
    }

    public void close() {
        if (this.webDriver != null) {
            this.webDriver.quit();
            this.webDriver = null;
        }
    }

    private Session() {
        Runtime.getRuntime().addShutdownHook(new Thread(Session.this::close));
    }
}
