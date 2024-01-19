package org.coursework.config;

import org.coursework.config.enums.Browsers;

import static org.coursework.utils.PropertyUtil.getProperty;

public class BrowserConfig {
    public static final boolean HEADLESS_FLAG = Boolean.valueOf(getProperty("headless", "true"));
    public static final Browsers WEB_BROWSER = Browsers.valueOf(getProperty("browser", "CHROME"));
}
