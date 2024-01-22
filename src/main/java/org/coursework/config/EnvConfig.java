package org.coursework.config;

import org.coursework.base.BaseConfig;
import org.coursework.config.common.Param;
import org.coursework.config.enums.Environment;

import java.util.Properties;

import static org.coursework.utils.PropertyUtil.getProperty;

public class EnvConfig extends BaseConfig {
    private static Environment env = Environment.valueOf(getProperty("env", "LOCAL"));

    public static final Param HTTP_BASE_PROTOCOL = new Param("http.base.protocol", "http", true, getEnvProperties(env));
    public static final Param HTTP_BASE_URL = new Param("http.base.url", "localhost", true, getEnvProperties(env));
    public static final Param HTTP_BASE_PORT = new Param("http.base.port", "80", true, getEnvProperties(env));
    public static final Param JSONRPC_VERSION = new Param("api.jsonrpc.version", "2.0", true, getEnvProperties(env));
    public static final Param ADMIN_USERNAME = new Param("admin.username", "admin", true, getEnvProperties(env));
    public static final Param ADMIN_PASSWORD = new Param("admin.password", "admin", true, getEnvProperties(env));
    public static final Param SELENIARM_STANDALONE_CHROMIUM_HOST = new Param("seleniarm.chromium.host", "localhost", true, getEnvProperties(env));
    public static final Param SELENIARM_STANDALONE_CHROMIUM_PORT = new Param("seleniarm.chromium.port", "4444", true, getEnvProperties(env));
    public static final Param SELENIARM_STANDALONE_FIREFOX_HOST = new Param("seleniarm.firefox.host", "localhost", true, getEnvProperties(env));
    public static final Param SELENIARM_STANDALONE_FIREFOX_PORT = new Param("seleniarm.firefox.port", "4444", true, getEnvProperties(env));
    public static final Param ENV_NAME = new Param("env", "local", true, getEnvProperties(env));

    private static Properties envProperties;

    public static String getBaseURL() {
        return String.format("%s://%s", EnvConfig.HTTP_BASE_PROTOCOL.value, EnvConfig.HTTP_BASE_URL.value);
    }

    private static Properties getEnvProperties(Environment env) {
        if (envProperties == null) {
            envProperties = new Properties();
            environmentName = env.getEnvName();
            envProperties.putAll(getResourceProperties("common.properties"));
            envProperties.putAll(getResourceProperties("env/" + environmentName + ".properties"));
        }
        return envProperties;
    }
}
