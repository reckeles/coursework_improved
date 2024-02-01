package org.coursework.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.coursework.api.model.Authorization;

import java.net.URI;
import java.net.URISyntaxException;

import static org.coursework.config.EnvConfig.*;

abstract public class RequestSender {
    public static <T> Response sendRequest(T body, Authorization credentials) {
        return RestAssured.given().auth()
                .basic(credentials.getUsername(), credentials.getPassword())
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(body)
                .when()
                .post(getAPIURL())
                .then()
                .statusCode(200)
                .extract().response();
    }

    private static URI getAPIURL() {
        String url = getBaseURL() + ":" + HTTP_BASE_PORT.value + "/jsonrpc.php";
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException("URL has wrong format: " + url, e);
        }
    }
}
