package org.coursework.config;

import org.coursework.base.BaseConfig;
import org.coursework.config.common.InitErrors;
import org.coursework.config.common.Param;
import org.coursework.config.enums.Environment;

import java.util.Properties;

import static org.coursework.utils.PropertyUtil.getProperty;

public class EnvConfig extends BaseConfig {
    private static EnvProperties envProperties;
//    private static String baseUrl;

    public static EnvProperties getEnvProperties(){
        if(envProperties == null){
            Environment env = Environment.valueOf(getProperty("env"));
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
            InitErrors.showErrors();

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
