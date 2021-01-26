package com.fitcrew.validatorservice.core.config;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ReactiveConnectorCreator {

	public <T> Mono<T> createMono(WebClient webClient,
								  String uri,
								  Class<T> responseType) {
		return webClient
				.get()
				.uri(uri)
				.retrieve()
				.bodyToMono(responseType)
				.doOnError(Mono::error);
	}
}