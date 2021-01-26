package com.fitcrew.validatorservice.core.config;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ClientServiceWebClient {

    private static final String SERVICE_URL = "http://localhost:8012/fitcrewclientservice/api/client";

    @Getter
    private final WebClient webClient;

    public ClientServiceWebClient(final WebClient.Builder builder) {
        this.webClient = builder.baseUrl(SERVICE_URL).build();
    }
}
