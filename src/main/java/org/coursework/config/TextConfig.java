package org.coursework.config;

import org.coursework.base.BaseConfig;
import org.coursework.config.common.InitErrors;
import org.coursework.config.common.Param;
import org.coursework.config.enums.TextLocale;

import java.util.Properties;

import static org.coursework.utils.PropertyUtil.getProperty;

public class TextConfig extends BaseConfig {
    private static TextProperties textProperties;

    public static TextProperties getTextProperties() {
        if (textProperties == null) {
            TextLocale locale = TextLocale.valueOf(getProperty("locale").toUpperCase());
            String propertyPath = "texts/" + locale.getName() + ".properties";
            Properties localeProperties = setProperties(propertyPath);

            Param loginPageBadCreds = new Param("login.badCredsAlert", localeProperties);
            Param taskStatusClosedLabel = new Param("task.status.closed", localeProperties);
            Param projectStatusOpened = new Param("project.status.opened", localeProperties);
            InitErrors.showErrors();

            textProperties = new TextProperties(
                    loginPageBadCreds.value,
                    taskStatusClosedLabel.value,
                    projectStatusOpened.value);
        }
        return textProperties;
    }

    public static class TextProperties {
        public final String loginPageBadCreds;
        public final String taskStatusClosedLabel;
        public final String projectStatusOpened;

        public TextProperties(String loginPageBadCreds, String taskStatusClosedLabel, String projectStatusOpened) {
            this.loginPageBadCreds = loginPageBadCreds;
            this.taskStatusClosedLabel = taskStatusClosedLabel;
            this.projectStatusOpened = projectStatusOpened;
        }
    }

    private TextConfig() {
    }
}
