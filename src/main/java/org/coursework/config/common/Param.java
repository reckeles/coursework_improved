package org.coursework.config.common;

import java.util.Properties;

public class Param {
    private final String name;
    public final String value;

    public Param(String name, Properties properties) {
        this(name, null, properties);
    }

    public Param(String name, String defaultValue, Properties properties) {
        this.name = name;

        String tmpVal = properties.getProperty(name);
        if (tmpVal == null) {
            tmpVal = defaultValue;
        }
        if (tmpVal == null) {
            InitErrors.addError("Parameter value is not found. Parameter " + name);
        }
        value = tmpVal;
    }

    public boolean isTrue() {
        return "true".equalsIgnoreCase(value);
    }

    public Integer asInt() {
        return Integer.parseInt(value);
    }
}
