package org.coursework.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseAPIResponse<T> {
    private String jsonrpc;
    private long id;
    private T result;
    private Object error;
}
