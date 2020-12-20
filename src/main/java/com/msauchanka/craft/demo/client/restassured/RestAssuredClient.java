package com.msauchanka.craft.demo.client.restassured;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredClient {

    public Response get(String url, Map<String, String> params) {
        return given().
                log().method().
                params(params).
        when().
                get(url).
        then().
                log().status().
                extract().
                response();
    }

}
