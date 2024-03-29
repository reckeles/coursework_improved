package org.coursework.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseConfig {

    protected static Properties setProperties(String resourceFilePath) {
        Properties properties = new Properties();
        properties.putAll(getResourceProperties(resourceFilePath));
        return properties;
    }

    private static Properties getResourceProperties(String resourceFilePath) {
        Properties props = new Properties();
        InputStream iStream = null;
        try {
            iStream = BaseConfig.class.getClassLoader().getResourceAsStream(resourceFilePath);
            if (iStream == null) {
                throw new RuntimeException("Resource file not found " + resourceFilePath);
            }
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
}
