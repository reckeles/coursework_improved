package org.coursework;

import org.coursework.base.BaseWebDriverCreator;
import org.coursework.config.EnvConfig;
import org.coursework.webDriverInitialization.GridWebDriverCreator;
import org.coursework.webDriverInitialization.LocalWebDriverCreator;
import org.openqa.selenium.WebDriver;

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
        if (this.webDriver == null) {
            setupWebDriver();
        }
        return this.webDriver;
    }

    private void setupWebDriver() {
        if ("local".equalsIgnoreCase(EnvConfig.ENV_NAME.value)) {
            this.webDriverCreator = new LocalWebDriverCreator();
        } else if ("CI".equalsIgnoreCase(EnvConfig.ENV_NAME.value)) {
            this.webDriverCreator = new GridWebDriverCreator();
        } else {
            throw new RuntimeException("Unsupported env: " + EnvConfig.ENV_NAME.value);
        }

        this.webDriver = this.webDriverCreator.createWebDriver();
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
