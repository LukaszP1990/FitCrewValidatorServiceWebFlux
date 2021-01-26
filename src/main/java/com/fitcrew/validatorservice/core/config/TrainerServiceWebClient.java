package com.fitcrew.validatorservice.core.config;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TrainerServiceWebClient {

    private static final String SERVICE_URL = "http://localhost:8013/fitcrewtrainerservice/api/trainer";

    @Getter
    private final WebClient webClient;

    public TrainerServiceWebClient(final WebClient.Builder builder) {
        this.webClient = builder.baseUrl(SERVICE_URL).build();
    }
}
