package com.msauchanka.craft.demo.test;

import com.msauchanka.craft.demo.model.response.ResponseBody;
import com.msauchanka.craft.demo.utility.TestDataUtils;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.msauchanka.craft.demo.constant.Constants.TEST_COMPLETED_LOG_PATTERN;
import static com.msauchanka.craft.demo.constant.Constants.TEST_STARTED_LOG_PATTERN;
import static java.lang.String.format;

public class PositiveTests extends BaseTest {

    @Test(groups = { "regression" }, dataProvider = "positiveTestDataProvider")
    public void positiveTest(String description, HashMap<String, String> params, ResponseBody expectedResponseBody) {
        logger.info(format(TEST_STARTED_LOG_PATTERN, description));
        Response response = getApiStep(params);
        responsePositiveValidationStep(response, expectedResponseBody);
        logger.info(format(TEST_COMPLETED_LOG_PATTERN, description));
    }

    @DataProvider
    public Object[][] positiveTestDataProvider() {
        String apiKeyOnlyTestDescription = "GET with registered api_key only";
        Map<String, String> apiKeyOnlyTestParams = TestDataUtils.QueryParametersExpected.registeredApiKey(nasaApiProperty.getApiKey());
        ResponseBody apiKeyOnlyExpectedBody = TestDataUtils.ResponseBodyOk.body(dateUtils.getTodayDate());

        String allParamsTestDescription = "GET with all params. Registered api_key.";
        String yesterdayDate = dateUtils.getYesterdayDate();
        Map<String, String> allParamsTestParams = TestDataUtils.QueryParametersExpected.allParams(nasaApiProperty.getApiKey(), yesterdayDate, String.valueOf(true));
        ResponseBody allParamsExpectedBody = TestDataUtils.ResponseBodyOk.body(yesterdayDate);

        return new Object[][] {
                {apiKeyOnlyTestDescription, apiKeyOnlyTestParams, apiKeyOnlyExpectedBody},
                {allParamsTestDescription, allParamsTestParams, allParamsExpectedBody}
        };
    }
}
