package org.coursework;

import org.coursework.config.EnvConfig;
import org.coursework.base.BaseWebDriver;
import org.coursework.webDriverInitialization.GridWebDriver;
import org.coursework.webDriverInitialization.LocalWebDriver;
import org.openqa.selenium.WebDriver;

public class Session {
    static final private ThreadLocal<Session> _instance = new ThreadLocal<>();

    private BaseWebDriver _baseWebDriver;
    private WebDriver _webdriver;

    static public Session get() {
        if (_instance.get() == null)
            _instance.set(new Session());
        return _instance.get();
    }

    public WebDriver webdriver() {
        if (this._webdriver == null) {
            if ("local".equalsIgnoreCase(EnvConfig.ENV_NAME.value)) {
                this._baseWebDriver = new LocalWebDriver();
            } else if ("CI".equalsIgnoreCase(EnvConfig.ENV_NAME.value)) {
                this._baseWebDriver = new GridWebDriver();
            } else {
                throw new RuntimeException("Unsupported env: " + EnvConfig.ENV_NAME.value);
            }

            this._webdriver = this._baseWebDriver.createDriver();
            this._webdriver.manage().window().maximize();
        }

        return this._webdriver;
    }

    public void close() {
        if (this._webdriver != null) {
            this._webdriver.quit();
            this._webdriver = null;
        }
    }

    private Session() {
        Runtime.getRuntime().addShutdownHook(new Thread(Session.this::close));
    }
}
