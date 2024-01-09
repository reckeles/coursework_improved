package org.coursework.utils;

public class PropertyUtil {
    public static String getProperty(String propertyName, String defaultValue){
        String property = System.getProperty(propertyName);
        if (property == null) {
            return defaultValue;
        }
        return property;
    }
}
