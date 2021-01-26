package com.fitcrew.validatorservice.core.provider;

import com.fitcrew.FitCrewAppModel.domain.dto.AdminDto;
import com.fitcrew.FitCrewAppModel.domain.dto.ClientDto;
import com.fitcrew.FitCrewAppModel.domain.dto.TrainerDto;
import com.fitcrew.validatorservice.core.config.AdminServiceWebClient;
import com.fitcrew.validatorservice.core.config.ReactiveConnectorCreator;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
public class DataValidationProvider {

    private final AdminServiceWebClient adminServiceWebClient;
    private final ReactiveConnectorCreator reactiveConnectorCreator;

    public DataValidationProvider(final AdminServiceWebClient adminServiceWebClient,
                                  final ReactiveConnectorCreator reactiveConnectorCreator) {
        this.adminServiceWebClient = adminServiceWebClient;
        this.reactiveConnectorCreator = reactiveConnectorCreator;
    }

    public Mono<AdminDto> getAdminById(String adminId) {
        return reactiveConnectorCreator.createMono(adminServiceWebClient.getWebClient(),
                buildPath(adminId, "/admin-id", "admin-id"),
                AdminDto.class);
    }

    public Mono<ClientDto> getClientById(String clientId) {
        return reactiveConnectorCreator.createMono(adminServiceWebClient.getWebClient(),
                buildPath(clientId, "/client-id", "client-id"),
                ClientDto.class);
    }

    public Mono<TrainerDto> getTrainerById(String trainerId) {
        return reactiveConnectorCreator.createMono(adminServiceWebClient.getWebClient(),
                buildPath(trainerId, "/trainer-id", "trainer-id"),
                TrainerDto.class);
    }

    private String buildPath(String adminId,
                             String path,
                             String queryParamName) {
        return UriComponentsBuilder.fromPath(path)
                .queryParam(queryParamName, adminId)
                .build()
                .toUriString();
    }
}
