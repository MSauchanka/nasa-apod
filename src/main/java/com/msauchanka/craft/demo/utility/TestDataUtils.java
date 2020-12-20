package com.msauchanka.craft.demo.utility;

import com.msauchanka.craft.demo.model.response.BadRequestResponseBody;
import com.msauchanka.craft.demo.model.response.ErrorResponseBody;
import com.msauchanka.craft.demo.model.response.ResponseBody;
import lombok.experimental.UtilityClass;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.msauchanka.craft.demo.constant.ErrorCode.API_KEY_INVALID;

@UtilityClass
public class TestDataUtils {

    public static final String HD_PARAM = "hd";
    public static final String DATE_PARAM = "date";
    public static final String API_KEY_PARAM = "api_key";

    public static final String SERVICE_VERSION_PARAM_VALUE = "v1";

    public static final String INVALID_API_KEY_ERROR_MESSAGE = "An invalid api_key was supplied. Get one at https://api.nasa.gov:443";
    public static final String UNEXPECTED_QUERY_PARAM_ERROR_MESSAGE = "Bad Request: incorrect field passed. Allowed request fields for apod method are 'concept_tags', 'date', 'hd', 'count', 'start_date', 'end_date', 'thumbs'";

    public static class QueryParametersError {

        public static Map<String, String> invalidApiKey() {
            Map<String, String> m = new HashMap<>();
            m.put(API_KEY_PARAM, UUID.randomUUID().toString());

            return m;
        }

        public static Map<String, String> unexpectedParam(String apiKey) {
            Map<String, String> m = new HashMap<>();
            m.put(API_KEY_PARAM, apiKey);
            m.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());

            return m;
        }

    }

    public static class QueryParametersExpected {

        public static Map<String, String> registeredApiKey(String apiKey) {
            Map<String, String> m = new HashMap<>();
            m.put(API_KEY_PARAM, apiKey);

            return m;
        }

        public static Map<String, String> allParams(String apiKey, String date, String hd) {
            Map<String, String> m = new HashMap<>();
            m.put(API_KEY_PARAM, apiKey);
            m.put(DATE_PARAM, date);
            m.put(HD_PARAM, hd);

            return m;
        }

    }

    public static class ResponseBodyError {

        public static ErrorResponseBody invalidApiKey() {
            ErrorResponseBody.Error error = new ErrorResponseBody.Error(API_KEY_INVALID, INVALID_API_KEY_ERROR_MESSAGE);
            return ErrorResponseBody.builder()
                    .error(error)
                    .build();
        }

        public static BadRequestResponseBody unexpectedQueryParam() {
            return BadRequestResponseBody.builder()
                    .code(HttpStatus.SC_BAD_REQUEST)
                    .msg(UNEXPECTED_QUERY_PARAM_ERROR_MESSAGE)
                    .serviceVersion(SERVICE_VERSION_PARAM_VALUE)
                    .build();
        }
    }

    public static class ResponseBodyOk {

        public static ResponseBody body(String date) {
            return ResponseBody.builder()
                    .date(date)
                    .serviceVersion(SERVICE_VERSION_PARAM_VALUE)
                    .build();
        }
    }
}
