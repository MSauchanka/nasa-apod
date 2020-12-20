package com.msauchanka.craft.demo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("nasa.apod")
public class NasaApodApiProperty {
    private String uri;
    private String dateFormat;
}
