package org.coursework.config;

import org.coursework.base.BaseConfig;
import org.coursework.config.common.Param;
import org.coursework.config.enums.TextLocale;

import java.util.Properties;

public class TextConfig extends BaseConfig {
    private static TextProperties textProperties;

    public static TextProperties getTextProperties() {
        if (textProperties == null) {
            TextLocale locale = TextLocale.valueOf(System.getProperty("locale"));
            String propertyPath = "texts/" + locale.getName() + ".properties";
            Properties localeProperties = setProperties(propertyPath);

            Param loginPageBadCreds = new Param("login.badCredsAlert", localeProperties);
            Param taskStatusClosedLabel = new Param("task.status.closed", localeProperties);

            textProperties = new TextProperties(loginPageBadCreds.value, taskStatusClosedLabel.value);
        }
        return textProperties;
    }

    public static class TextProperties {
        public final String loginPageBadCreds;
        public final String taskStatusClosedLabel;

        public TextProperties(String loginPageBadCreds, String taskStatusClosedLabel) {
            this.loginPageBadCreds = loginPageBadCreds;
            this.taskStatusClosedLabel = taskStatusClosedLabel;
        }
    }
}
