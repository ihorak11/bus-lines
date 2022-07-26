package com.example.buslines.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource(value = "classpath:application.properties")
public class ApplicationProperties {

    @Value("${api.sl.url}")
    private String apiSlUrl;

    @Value("${api.sl.api-key}")
    private String slApiKey;

    @Value("api.sl.path")
    private String slApiPath;
}
