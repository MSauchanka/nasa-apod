package com.msauchanka.craft.demo.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msauchanka.craft.demo.property.NasaApiProperty;
import com.msauchanka.craft.demo.utility.DatabindTestUtils;
import com.msauchanka.craft.demo.utility.DateTestUtils;
import com.msauchanka.craft.demo.client.restassured.RestAssuredClient;
import com.msauchanka.craft.demo.property.NasaApodApiProperty;
import com.msauchanka.craft.demo.utility.URITestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.msauchanka.craft.demo"})
public class FrameworkConfiguration {

    @Bean
    public RestAssuredClient restAssuredClient() {
        return new RestAssuredClient();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public DatabindTestUtils databindUtils(ObjectMapper mapper) {
        return new DatabindTestUtils(mapper);
    }

    @Bean
    public DateTestUtils dateUtils(NasaApodApiProperty property) {
        return new DateTestUtils(property.getDateFormat());
    }

    @Bean
    public URITestUtils uriTestUtils(NasaApiProperty np, NasaApodApiProperty ap) {
        return new URITestUtils(np.getScheme(), np.getHost(), ap.getUri());
    }
}
