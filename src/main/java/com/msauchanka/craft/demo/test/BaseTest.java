package com.msauchanka.craft.demo.test;

import com.msauchanka.craft.demo.client.restassured.RestAssuredClient;
import com.msauchanka.craft.demo.configuration.FrameworkConfiguration;
import com.msauchanka.craft.demo.model.response.BadRequestResponseBody;
import com.msauchanka.craft.demo.model.response.ErrorResponseBody;
import com.msauchanka.craft.demo.model.response.ResponseBody;
import com.msauchanka.craft.demo.property.NasaApiProperty;
import com.msauchanka.craft.demo.utility.DatabindTestUtils;
import com.msauchanka.craft.demo.utility.DateTestUtils;
import com.msauchanka.craft.demo.utility.URITestUtils;
import com.msauchanka.craft.demo.validation.ResponseValidator;
import io.restassured.response.Response;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { FrameworkConfiguration.class })
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public NasaApiProperty nasaApiProperty;

    @Autowired
    public RestAssuredClient restAssuredClient;

    @Autowired
    public DatabindTestUtils databindTestUtils;

    @Autowired
    public DateTestUtils dateUtils;

    @Autowired
    public URITestUtils uriUtils;

    Response getApiStep(Map<String, String> params) {
        return restAssuredClient.get(uriUtils.getDefaultUrl(), params);
    }

    void responsePositiveValidationStep(Response response, ResponseBody expectedBody) {
        logger.info("Executing response positive validation step");
        ResponseValidator.StatusCode.verifyOk(response);
        ResponseValidator.Headers.verifyContentType(response);
        ResponseBody actualBody = databindTestUtils.deserializeJsonTo(ResponseBody.class, response.getBody().asString());
        ResponseValidator.Body.verifyBody(actualBody, expectedBody);
        responseInternalLinksValidation(actualBody);
    }

    void responseForbiddenValidationStep(Response response, ErrorResponseBody expectedBody) {
        logger.info("Executing forbidden response validation step");
        ResponseValidator.StatusCode.verifyForbidden(response);
        ResponseValidator.Headers.verifyContentType(response);
        ErrorResponseBody actualBody = databindTestUtils.deserializeJsonTo(ErrorResponseBody.class, response.getBody().asString());
        ResponseValidator.Body.verifyBody(actualBody, expectedBody);
    }

    void responseBadRequestValidationStep(Response response, BadRequestResponseBody expectedBody) {
        logger.info("Executing bad request response validation step");
        ResponseValidator.StatusCode.verifyBadRequest(response);
        ResponseValidator.Headers.verifyContentType(response);
        BadRequestResponseBody actualBody = databindTestUtils.deserializeJsonTo(BadRequestResponseBody.class, response.getBody().asString());
        ResponseValidator.Body.verifyBody(actualBody, expectedBody);
    }

    void responseInternalLinksValidation(ResponseBody actualBody) {
        logger.info("Executing 'response.url' field's link validation");
        Response response = restAssuredClient.get(actualBody.getUrl(), new HashMap<>());
        ResponseValidator.StatusCode.verifyOk(response);
    }
}
