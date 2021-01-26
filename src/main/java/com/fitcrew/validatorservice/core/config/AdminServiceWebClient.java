package com.fitcrew.validatorservice.core.config;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AdminServiceWebClient {

    private static final String SERVICE_URL = "http://localhost:8011/fitcrewadminservice/api/admin";

    @Getter
    private final WebClient webClient;

    public AdminServiceWebClient(final WebClient.Builder builder) {
        this.webClient = builder.baseUrl(SERVICE_URL).build();
    }
}