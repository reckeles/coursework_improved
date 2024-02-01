package org.coursework.base;

import org.coursework.config.EnvConfig;
import org.coursework.config.common.InitErrors;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

abstract public class BaseConfig {
    protected static Properties getResourceProperties(String resourceFilePath) {
        Properties props = new Properties();
        InputStream iStream = null;
        try {
            iStream = EnvConfig.class.getClassLoader().getResourceAsStream(resourceFilePath);
            if (iStream == null)
                throw new RuntimeException("Resource file not found " + resourceFilePath);
            props.load(iStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not read resource properties file " + resourceFilePath, e);
        } finally {
            try {
                if (iStream != null)
                    iStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Could not close input stream", e);
            }
        }
        return props;
    }

    protected static Properties setProperties(String resourceFilePath) {
        Properties properties = new Properties();
        properties.putAll(getResourceProperties(resourceFilePath));
        return properties;
    }

    static {
        InitErrors.showErrors();
    }
}
