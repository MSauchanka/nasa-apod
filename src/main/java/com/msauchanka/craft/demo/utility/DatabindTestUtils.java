package com.msauchanka.craft.demo.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.testng.Assert;

import static java.lang.String.format;

@AllArgsConstructor
public class DatabindTestUtils {

    private ObjectMapper mapper;

    public <T> T deserializeJsonTo(Class<T> clazz, String json) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            Assert.fail(format("JsonProcessingException appeared while deserialize to %s: %s", clazz.getSimpleName(), e.getMessage()));
        }

        return null;
    }

}
