package org.coursework.config;

import org.coursework.base.BaseConfig;
import org.coursework.config.common.Param;
import org.coursework.config.enums.TextLocale;

import java.util.Properties;

public class TextConfig extends BaseConfig {
    private static TextLocale locale = TextLocale.valueOf(System.getProperty("locale"));
    private static Properties localeProperties = getProperties(locale);

    public static final Param LOGIN_PAGE_BAD_CREDS_ALERT = new Param("login.badCredsAlert", localeProperties);
    public static final Param TASK_STATUS_CLOSED_LABEL = new Param("task.status.closed", localeProperties);

    private static Properties getProperties(TextLocale locale) {
        if (localeProperties == null) {
            localeProperties = new Properties();
            localeProperties.putAll(getResourceProperties("texts/" + locale.getName() + ".properties"));
        }
        return localeProperties;
    }
}
