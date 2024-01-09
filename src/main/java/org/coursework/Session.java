package org.coursework;

import org.coursework.config.EnvConfig;
import org.coursework.testbed.BaseTestbed;
import org.coursework.testbed.TestbedGrid;
import org.coursework.testbed.TestbedLocal;
import org.openqa.selenium.WebDriver;

public class Session {
    static final private ThreadLocal<Session> _instance = new ThreadLocal<>();

    private BaseTestbed _testbed;
    private WebDriver _webdriver;

    static public Session get() {
        if (_instance.get() == null)
            _instance.set(new Session());
        return _instance.get();
    }

    public WebDriver webdriver() {
        if (this._webdriver == null) {
            if ("local".equalsIgnoreCase(EnvConfig.TESTBED.value)) {
                this._testbed = new TestbedLocal();
            } else if ("grid".equalsIgnoreCase(EnvConfig.TESTBED.value)) {
                this._testbed = new TestbedGrid();
            } else
                throw new RuntimeException("Unsupported testbed: " + EnvConfig.TESTBED.value);

            this._webdriver = this._testbed.createDriver();
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
