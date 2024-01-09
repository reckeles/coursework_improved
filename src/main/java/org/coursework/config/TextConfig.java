package org.coursework.config;

import org.coursework.base.BaseConfig;
import org.coursework.config.common.Param;
import org.coursework.config.enums.TextLocale;

import java.util.Properties;

import static org.coursework.utils.PropertyUtil.getProperty;

public class TextConfig extends BaseConfig {
    private static TextLocale locale = TextLocale.valueOf(getProperty("locale", "EN"));

    public static final Param LOGIN_PAGE_BAD_CREDS_ALERT = new Param("login.badCredsAlert", "Bad username or password", true, getEnvProperties(locale));
    public static final Param TASK_STATUS_CLOSED_LABEL = new Param("task.status.closed", "closed", true, getEnvProperties(locale));

    private static Properties envProperties;

    private static Properties getEnvProperties(TextLocale locale) {
        if (envProperties == null) {
            envProperties = new Properties();
            environmentName = locale.getLocale();
            envProperties.putAll(getResourceProperties("common.properties"));
            envProperties.putAll(getResourceProperties("texts/" + environmentName + ".properties"));
        }
        return envProperties;
    }
}
