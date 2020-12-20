package com.msauchanka.craft.demo.validation;

import com.msauchanka.craft.demo.model.response.BadRequestResponseBody;
import com.msauchanka.craft.demo.model.response.ErrorResponseBody;
import com.msauchanka.craft.demo.model.response.ResponseBody;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static java.lang.String.format;

@UtilityClass
public class ResponseValidator {

    private static final String UNEXPECTED_RESPONSE_STATUS_CODE_MESSAGE = "Unexpected response status code -> ERROR!";
    private static final String UNEXPECTED_RESPONSE_HEADER = "Unexpected response header -> ERROR!";
    private static final String UNEXPECTED_RESPONSE_BODY_PARAMETER = "Unexpected value for '%s' field: '%s' -> ERROR";

    public static class StatusCode {

        public static void verifyOk(Response response) {
            Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, UNEXPECTED_RESPONSE_STATUS_CODE_MESSAGE);
        }

        public static void verifyForbidden(Response response) {
            Assert.assertEquals(response.statusCode(), HttpStatus.SC_FORBIDDEN, UNEXPECTED_RESPONSE_STATUS_CODE_MESSAGE);
        }

        public static void verifyBadRequest(Response response) {
            Assert.assertEquals(response.statusCode(), HttpStatus.SC_BAD_REQUEST, UNEXPECTED_RESPONSE_STATUS_CODE_MESSAGE);
        }
    }

    public static class Headers {

        public static void verifyContentType(Response response) {
            Assert.assertEquals(response.getContentType(), ContentType.APPLICATION_JSON.getMimeType(), UNEXPECTED_RESPONSE_HEADER);
        }
    }

    public static class Body {

        public static void verifyBody(ResponseBody actual, ResponseBody expected) {
            SoftAssert sa = new SoftAssert();
            sa.assertEquals(actual.getDate(), expected.getDate(), format(UNEXPECTED_RESPONSE_BODY_PARAMETER, "date", actual.getDate()));
            sa.assertEquals(actual.getServiceVersion(), expected.getServiceVersion(), format(UNEXPECTED_RESPONSE_BODY_PARAMETER, "service_version", actual.getServiceVersion()));

            sa.assertTrue(StringUtils.isNotEmpty(actual.getExplanation()), format(UNEXPECTED_RESPONSE_BODY_PARAMETER, "explanation", actual.getExplanation()));
            sa.assertTrue(StringUtils.isNotEmpty(actual.getMediaType()), format(UNEXPECTED_RESPONSE_BODY_PARAMETER, "media_type", actual.getMediaType()));
            sa.assertTrue(StringUtils.isNotEmpty(actual.getTitle()), format(UNEXPECTED_RESPONSE_BODY_PARAMETER, "title", actual.getTitle()));
            sa.assertTrue(StringUtils.isNotEmpty(actual.getUrl()), format(UNEXPECTED_RESPONSE_BODY_PARAMETER, "url", actual.getUrl()));

            sa.assertAll();
        }

        public static void verifyBody(ErrorResponseBody actual, ErrorResponseBody expected) {
            SoftAssert sa = new SoftAssert();
            if (actual.getError() != null) {
                ErrorResponseBody.Error actualError = actual.getError();
                ErrorResponseBody.Error expectedError = expected.getError();
                sa.assertEquals(actualError.getCode(), expectedError.getCode(), format(UNEXPECTED_RESPONSE_BODY_PARAMETER, "code", actualError.getCode()));
                sa.assertEquals(actualError.getMessage(), expectedError.getMessage(), format(UNEXPECTED_RESPONSE_BODY_PARAMETER, "message", actualError.getMessage()));
            } else {
                sa.fail(format(UNEXPECTED_RESPONSE_BODY_PARAMETER, "error", actual.getError()));
            }

            sa.assertAll();
        }

        public static void verifyBody(BadRequestResponseBody actual, BadRequestResponseBody expected) {
            SoftAssert sa = new SoftAssert();
            sa.assertEquals(actual.getCode(), expected.getCode(), format(UNEXPECTED_RESPONSE_BODY_PARAMETER, "code", actual.getCode()));
            sa.assertEquals(actual.getMsg(), expected.getMsg(), format(UNEXPECTED_RESPONSE_BODY_PARAMETER, "message", actual.getMsg()));
            sa.assertEquals(actual.getServiceVersion(), expected.getServiceVersion(), format(UNEXPECTED_RESPONSE_BODY_PARAMETER, "service_version", actual.getServiceVersion()));

            sa.assertAll();
        }

    }
}
