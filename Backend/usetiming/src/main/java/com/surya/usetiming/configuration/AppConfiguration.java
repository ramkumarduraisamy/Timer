package com.surya.usetiming.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {
    @Value("${microsoftGraphApi.baseUrl}")
    private String baseUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders headers() {
        return new HttpHeaders();
    }

    @Bean
    public String getBaseUrl() {
        return this.baseUrl;
    }
}
