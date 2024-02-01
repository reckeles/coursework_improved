package org.coursework.base;

import lombok.Getter;
import org.coursework.config.EnvConfig;


@Getter
public class BaseAPIRequestBody<T> {
    private String jsonrpc;
    private String method;
    private long id;
    private T params;

    public BaseAPIRequestBody(String method, long id, T params) {
        this.jsonrpc = EnvConfig.getEnvProperties().jsonrpcVersion;
        this.method = method;
        this.id = id;
        this.params = params;
    }
}
