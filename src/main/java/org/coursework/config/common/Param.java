package org.coursework.config.common;

import org.coursework.base.BaseConfig;

import java.util.Properties;

public class Param {
    public final String name;
    public final String value;

    public Param(String name) {
        this(name, null, false, null);
    }

    public Param(String name, String defaultValue) {
        this(name, defaultValue, false, null);
    }

    public Param(String name, String defaultValue, boolean isSys, Properties properties) {
        this.name = name;
        String tmpVal = null;
        if (isSys) {
            tmpVal = System.getProperty(name);
            if (tmpVal == null)
                tmpVal = System.getenv(name);
        }
        if (tmpVal == null)
            tmpVal = properties.getProperty(name);
        if (tmpVal == null)
            tmpVal = defaultValue;
        if (tmpVal == null)
            InitErrors.addError("Parameter value is not found. Parameter " + name);
        value = tmpVal;
//        EnvConfig._paramsList.add(this);
    }

    public boolean isTrue() {
        return "true".equalsIgnoreCase(value);
    }

    public Integer asInt() {
        return Integer.parseInt(value);
    }
}
