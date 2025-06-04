package com.api.zipcode.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.urls")
@Data
public class EnvironmentConstants {

    private String openStreetUrl;
    private String openMeteoUrl;
}