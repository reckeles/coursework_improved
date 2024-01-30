package org.coursework.config;

import org.coursework.base.BaseConfig;
import org.coursework.config.common.Param;
import org.coursework.config.enums.Environment;

import java.util.Properties;

public class EnvConfig extends BaseConfig {
    private static Environment env = Environment.valueOf(System.getProperty("env"));
    private static Properties envProperties = getProperties(env);

    public static final Param HTTP_BASE_PROTOCOL = new Param("http.base.protocol", envProperties);
    public static final Param HTTP_BASE_URL = new Param("http.base.url", envProperties);
    public static final Param HTTP_BASE_PORT = new Param("http.base.port", envProperties);
    public static final Param JSONRPC_VERSION = new Param("api.jsonrpc.version", envProperties);
    public static final Param ADMIN_USERNAME = new Param("admin.username", envProperties);
    public static final Param ADMIN_PASSWORD = new Param("admin.password", envProperties);
    public static final Param SELENIARM_STANDALONE_CHROMIUM_HOST = new Param("seleniarm.chromium.host", envProperties);
    public static final Param SELENIARM_STANDALONE_CHROMIUM_PORT = new Param("seleniarm.chromium.port", envProperties);
    public static final Param SELENIARM_STANDALONE_FIREFOX_HOST = new Param("seleniarm.firefox.host", envProperties);
    public static final Param SELENIARM_STANDALONE_FIREFOX_PORT = new Param("seleniarm.firefox.port", envProperties);
    public static final Param ENV_NAME = new Param("env", envProperties);

    public static String getBaseURL() {
        return String.format("%s://%s", HTTP_BASE_PROTOCOL.value, HTTP_BASE_URL.value);
    }

    private static Properties getProperties(Environment env) {
        if (envProperties == null) {
            envProperties = new Properties();
            envProperties.putAll(getResourceProperties("env/" + env.getName() + ".properties"));
        }
        return envProperties;
    }
}
