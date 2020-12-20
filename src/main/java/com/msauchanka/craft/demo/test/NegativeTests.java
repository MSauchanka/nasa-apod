package com.msauchanka.craft.demo.test;

import com.msauchanka.craft.demo.model.response.BadRequestResponseBody;
import com.msauchanka.craft.demo.model.response.ErrorResponseBody;
import com.msauchanka.craft.demo.utility.TestDataUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static com.msauchanka.craft.demo.constant.Constants.TEST_COMPLETED_LOG_PATTERN;
import static com.msauchanka.craft.demo.constant.Constants.TEST_STARTED_LOG_PATTERN;
import static java.lang.String.format;

public class NegativeTests extends BaseTest {

    @Test(groups = { "regression" }, description = "GET with invalid api_key")
    public void invalidApiKeyTest() {
        String description = "GET with invalid api_key";
        logger.info(format(TEST_STARTED_LOG_PATTERN, description));
        Map<String, String> queryParams = TestDataUtils.QueryParametersError.invalidApiKey();
        ErrorResponseBody expectedBody = TestDataUtils.ResponseBodyError.invalidApiKey();

        Response response = getApiStep(queryParams);
        responseForbiddenValidationStep(response, expectedBody);
        logger.info(format(TEST_COMPLETED_LOG_PATTERN, description));
    }

    @Test(groups = { "regression" }, description = "GET with unexpected query param")
    public void unexpectedQueryParamTest() {
        String description = "GET with unexpected query param";
        logger.info(format(TEST_STARTED_LOG_PATTERN, description));
        Map<String, String> queryParams = TestDataUtils.QueryParametersError.unexpectedParam(nasaApiProperty.getApiKey());
        BadRequestResponseBody expectedBody = TestDataUtils.ResponseBodyError.unexpectedQueryParam();

        Response response = getApiStep(queryParams);
        responseBadRequestValidationStep(response, expectedBody);
        logger.info(format(TEST_COMPLETED_LOG_PATTERN, description));
    }
}
