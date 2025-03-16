package com.surya.usetiming.configuration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "app.config")
public class AppConfig {
    private String graphAPIUrl;
    private String appRegistrationId;

    public String getGraphAPIUrl() {
        return graphAPIUrl;
    }

    public String getAppRegistrationId() {
        return appRegistrationId;
    }

    public void setGraphAPIUrl(String graphAPIUrl) {
        this.graphAPIUrl = graphAPIUrl;
    }

    public void setAppRegistrationId(String appRegistrationId) {
        this.appRegistrationId = appRegistrationId;
    }
}
