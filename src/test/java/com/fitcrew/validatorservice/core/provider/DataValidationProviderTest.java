package com.fitcrew.validatorservice.core.provider;

import com.fitcrew.FitCrewAppModel.domain.dto.AdminDto;
import com.fitcrew.FitCrewAppModel.domain.dto.ClientDto;
import com.fitcrew.FitCrewAppModel.domain.dto.TrainerDto;
import com.fitcrew.validatorservice.core.config.AdminServiceWebClient;
import com.fitcrew.validatorservice.core.config.ReactiveConnectorCreator;
import com.fitcrew.validatorservice.validator.util.ClientUtil;
import com.fitcrew.validatorservice.validator.util.TrainerUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.fitcrew.validatorservice.validator.util.AdminUtil.PASSWORD;
import static com.fitcrew.validatorservice.validator.util.AdminUtil.*;
import static com.fitcrew.validatorservice.validator.util.ClientUtil.*;
import static com.fitcrew.validatorservice.validator.util.TrainerUtil.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DataValidationProviderTest {

    private final AdminServiceWebClient adminServiceWebClient = mock(AdminServiceWebClient.class);
    private final ReactiveConnectorCreator reactiveConnectorCreator = mock(ReactiveConnectorCreator.class);
    private final DataValidationProvider dataValidationProvider =
            new DataValidationProvider(adminServiceWebClient, reactiveConnectorCreator);

    @Test
    void shouldGetAdminById() {
        //given
        var admin = getAdminDto(ADMIN_ID, ADMIN_EMAIL, ADMIN_FIRST_NAME, ADMIN_LAST_NAME, PASSWORD);
        when(reactiveConnectorCreator.createMono(any(), anyString(), any()))
                .thenReturn(Mono.just(admin));

        //when
        var result = dataValidationProvider.getAdminById(ADMIN_ID);

        //then
        StepVerifier.create(result)
                .expectSubscription()
                .expectNextMatches(this::checkAdminDtoAssertions)
                .verifyComplete();
    }

    @Test
    void shouldNotGetAdminById() {
        //given
        when(reactiveConnectorCreator.createMono(any(), anyString(), any()))
                .thenReturn(Mono.empty());

        //when
        var result = dataValidationProvider.getAdminById(ADMIN_ID);

        //then
        StepVerifier.create(result)
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    void shouldGetClientById() {
        //given
        var client =
                getClientDto(CLIENT_ID, CLIENT_FIRST_NAME, CLIENT_LAST_NAME, CLIENT_DATE_OF_BIRTH, ClientUtil.PASSWORD, CLIENT_PHONE_NUMBER, CLIENT_EMAIL);
        when(reactiveConnectorCreator.createMono(any(), anyString(), any()))
                .thenReturn(Mono.just(client));

        //when
        var result = dataValidationProvider.getClientById(CLIENT_ID);

        //then
        StepVerifier.create(result)
                .expectSubscription()
                .expectNextMatches(this::checkClientDtoAssertions)
                .verifyComplete();
    }

    @Test
    void shouldNotGetClientById() {
        //given
        when(reactiveConnectorCreator.createMono(any(), anyString(), any()))
                .thenReturn(Mono.empty());

        //when
        var result = dataValidationProvider.getClientById(CLIENT_ID);

        //then
        StepVerifier.create(result)
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    void shouldGetTrainerById() {
        //given
        var trainer =
                getTrainerDto(TRAINER_ID, TRAINER_FIRST_NAME, TRAINER_LAST_NAME, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, TRAINER_DESCRIPTION, TrainerUtil.PASSWORD);
        when(reactiveConnectorCreator.createMono(any(), anyString(), any()))
                .thenReturn(Mono.just(trainer));

        //when
        var result = dataValidationProvider.getTrainerById(TRAINER_ID);

        //then
        StepVerifier.create(result)
                .expectSubscription()
                .expectNextMatches(this::checkTrainerDtoAssertions)
                .verifyComplete();
    }

    @Test
    void shouldNotGetTrainerById() {
        //given
        when(reactiveConnectorCreator.createMono(any(), anyString(), any()))
                .thenReturn(Mono.empty());

        //when
        var result = dataValidationProvider.getTrainerById(TRAINER_ID);

        //then
        StepVerifier.create(result)
                .expectSubscription()
                .verifyComplete();
    }

    private boolean checkAdminDtoAssertions(AdminDto adminDto) {
        return String.valueOf(1).concat(ADMIN_EMAIL).equals(adminDto.getEmail()) &&
                ADMIN_FIRST_NAME.equals(adminDto.getFirstName()) &&
                ADMIN_LAST_NAME.equals(adminDto.getLastName()) &&
                ADMIN_ENCRYPTED_PASSWORD.equals(adminDto.getEncryptedPassword()) &&
                String.valueOf(1).equals(adminDto.getAdminId());
    }

    private boolean checkClientDtoAssertions(ClientDto clientDto) {
        return CLIENT_FIRST_NAME.equals(clientDto.getFirstName()) &&
                CLIENT_LAST_NAME.equals(clientDto.getLastName()) &&
                CLIENT_DATE_OF_BIRTH.equals(clientDto.getDateOfBirth()) &&
                String.valueOf(1).concat(CLIENT_EMAIL).equals(clientDto.getEmail()) &&
                CLIENT_PHONE_NUMBER.equals(clientDto.getPhone());
    }

    private boolean checkTrainerDtoAssertions(TrainerDto trainerDto) {
        return TRAINER_FIRST_NAME.equals(trainerDto.getFirstName()) &&
                TRAINER_LAST_NAME.equals(trainerDto.getLastName()) &&
                TRAINER_DATE_OF_BIRTH.equals(trainerDto.getDateOfBirth()) &&
                String.valueOf(1).concat(TRAINER_EMAIL).equals(trainerDto.getEmail()) &&
                TRAINER_DESCRIPTION.equals(trainerDto.getSomethingAboutYourself()) &&
                TRAINER_PHONE_NUMBER.equals(trainerDto.getPhone());
    }
}