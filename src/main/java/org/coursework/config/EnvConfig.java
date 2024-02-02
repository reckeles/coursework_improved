package org.coursework.config;

import org.coursework.base.BaseConfig;
import org.coursework.config.common.Param;
import org.coursework.config.enums.Environment;

import java.util.Properties;

public class EnvConfig extends BaseConfig {
    private static EnvProperties envProperties;
//    private static String baseUrl;

    public static EnvProperties getEnvProperties(){
        if(envProperties == null){
            Environment env = Environment.valueOf(System.getProperty("env"));
            String propertyPath = "env/" + env.getName() + ".properties";
            Properties properties = setProperties(propertyPath);

            Param baseProtocol = new Param("http.base.protocol", properties);
            Param baseDomain = new Param("http.base.domain", properties);
            Param basePort = new Param("http.base.port", properties);
            Param jsonrpcVersion = new Param("api.jsonrpc.version", properties);
            Param adminUsername = new Param("admin.username", properties);
            Param adminPassword = new Param("admin.password", properties);
            Param seleniarmChromiumHost = new Param("seleniarm.chromium.host", properties);
            Param seleniarmChromiumPort = new Param("seleniarm.chromium.port", properties);
            Param seleniarmFirefoxHost = new Param("seleniarm.firefox.host", properties);
            Param seleniarmFirefoxPort = new Param("seleniarm.firefox.port", properties);
            Param envName = new Param("env", properties);
            String baseUrl = String.format("%s://%s", baseProtocol.value, baseDomain.value);

            envProperties = new EnvProperties(baseProtocol.value,
                    baseDomain.value,
                    basePort.value,
                    jsonrpcVersion.value,
                    adminUsername.value,
                    adminPassword.value,
                    seleniarmChromiumHost.value,
                    seleniarmChromiumPort.value,
                    seleniarmFirefoxHost.value,
                    seleniarmFirefoxPort.value,
                    envName.value,
                    baseUrl);
        }
        return envProperties;
    }

//    public static String getBaseURL(){
//        if (baseUrl == null){
//            if (envProperties == null){
//                getEnvProperties();
//            }
//            baseUrl = String.format("%s://%s", envProperties.baseProtocol, envProperties.baseDomain);
//        }
//        return baseUrl;
//    }
//    private static final String BASE_URL = String.format("%s://%s", HTTP_BASE_PROTOCOL.value, HTTP_BASE_URL.value);
//
//    public static String getBaseURL() {
//        return BASE_URL;
//    }
    
//    private static final Environment env = Environment.valueOf(System.getProperty("env"));
//    private static final String propertyPath = "env/" + env.getName() + ".properties";
//    private static final Properties envProperties = setProperties(propertyPath);
//
//    public static final Param HTTP_BASE_PROTOCOL = new Param("http.base.protocol", envProperties);
//    public static final Param HTTP_BASE_URL = new Param("http.base.domain", envProperties);
//    public static final Param HTTP_BASE_PORT = new Param("http.base.port", envProperties);
//    public static final Param JSONRPC_VERSION = new Param("api.jsonrpc.version", envProperties);
//    public static final Param ADMIN_USERNAME = new Param("admin.username", envProperties);
//    public static final Param ADMIN_PASSWORD = new Param("admin.password", envProperties);
//    public static final Param SELENIARM_STANDALONE_CHROMIUM_HOST = new Param("seleniarm.chromium.host", envProperties);
//    public static final Param SELENIARM_STANDALONE_CHROMIUM_PORT = new Param("seleniarm.chromium.port", envProperties);
//    public static final Param SELENIARM_STANDALONE_FIREFOX_HOST = new Param("seleniarm.firefox.host", envProperties);
//    public static final Param SELENIARM_STANDALONE_FIREFOX_PORT = new Param("seleniarm.firefox.port", envProperties);
//    public static final Param ENV_NAME = new Param("env", envProperties);
//
//    private static final String BASE_URL = String.format("%s://%s", HTTP_BASE_PROTOCOL.value, HTTP_BASE_URL.value);
//
//    public static String getBaseURL() {
//        return BASE_URL;
//    }

    public static class EnvProperties {
        public final String baseProtocol;
        public final String baseDomain;
        public final String basePort;
        public final String jsonrpcVersion;
        public final String adminUsername;
        public final String adminPassword;
        public final String seleniarmChromiumHost;
        public final String seleniarmChromiumPort;
        public final String seleniarmFirefoxHost;
        public final String seleniarmFirefoxPort;
        public final String envName;
        public final String baseUrl;

        public EnvProperties(String baseProtocol,
                             String baseDomain,
                             String basePort,
                             String jsonrpcVersion,
                             String adminUsername,
                             String adminPassword,
                             String seleniarmChromiumHost,
                             String seleniarmChromiumPort,
                             String seleniarmFirefoxHost,
                             String seleniarmFirefoxPort,
                             String envName,
                             String baseUrl) {
            this.baseProtocol = baseProtocol;
            this.baseDomain = baseDomain;
            this.basePort = basePort;
            this.jsonrpcVersion = jsonrpcVersion;
            this.adminUsername = adminUsername;
            this.adminPassword = adminPassword;
            this.seleniarmChromiumHost = seleniarmChromiumHost;
            this.seleniarmChromiumPort = seleniarmChromiumPort;
            this.seleniarmFirefoxHost = seleniarmFirefoxHost;
            this.seleniarmFirefoxPort = seleniarmFirefoxPort;
            this.envName = envName;
            this.baseUrl = baseUrl;
        }
    }
}
