package org.coursework.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.coursework.api.model.KanboardMethods;
import org.coursework.api.model.Authorization;
import org.coursework.base.BaseAPIRequestBody;
import org.coursework.base.BaseAPIResponse;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

abstract public class APIUtil {
    private static RetryTemplate retryTemplate = setRetryTemplate();

    public static <T, V> T sendGetRequest(KanboardMethods method, V params, Class<T> toValueTypeRef,
                                          Authorization authorization) {
        BaseAPIRequestBody<V> requestBody = new BaseAPIRequestBody<>(method.getMethod(), method.getId(), params);

        return retryTemplate.execute(context -> {
            Response response = RequestSender.sendRequest(requestBody, authorization);
            BaseAPIResponse<T> responseBody = response.getBody().as(BaseAPIResponse.class);

            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(
                    responseBody.getResult(),
                    toValueTypeRef
            );
        });
    }

    public static <V> Boolean sendRemoveRequest(KanboardMethods method, V params,
                                                Authorization authorization) {
        BaseAPIRequestBody<V> requestBody = new BaseAPIRequestBody<>(method.getMethod(), method.getId(), params);

        return retryTemplate.execute(context -> {
            Response response = RequestSender.sendRequest(requestBody, authorization);

            BaseAPIResponse<Boolean> responseBody = response.getBody().as(BaseAPIResponse.class);
            if (responseBody.getResult()) {
                return true;
            } else {
                throw new RuntimeException("Item wasn't deleted");
            }
        });
    }

    public static <T, V> T sendCreateRequest(KanboardMethods method, V params,
                                             Authorization authorization) {
        BaseAPIRequestBody<V> requestBody = new BaseAPIRequestBody<>(method.getMethod(), method.getId(), params);

        return retryTemplate.execute(context -> {
            Response response = RequestSender.sendRequest(requestBody, authorization);
            BaseAPIResponse<?> responseBody = response.getBody().as(BaseAPIResponse.class);

            var result = responseBody.getResult();
            if (result instanceof Boolean || responseBody.getError() != null) {
                throw new RuntimeException("Item wasn't created. Create Request failed. Status code: " + response.getStatusCode()
                        + " Body: " + response.getBody().print());
            } else {
                return (T) result;
            }
        });
    }

    private static RetryTemplate setRetryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(1000);
        retryTemplate.setBackOffPolicy(backOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }
}
