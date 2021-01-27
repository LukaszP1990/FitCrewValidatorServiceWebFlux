package com.fitcrew.validatorservice.factory;

import com.fitcrew.validatorservice.core.provider.DataValidationProvider;
import com.fitcrew.validatorservice.validator.admin.AdminValidator;
import com.fitcrew.validatorservice.validator.client.ClientValidator;
import com.fitcrew.validatorservice.validator.email.EmailValidator;
import com.fitcrew.validatorservice.validator.login.LoginValidator;
import com.fitcrew.validatorservice.validator.trainer.TrainerValidator;
import com.fitcrew.validatorservice.validator.training.TrainingValidator;
import com.fitcrew.validatorservice.validator.util.AdminUtil;
import com.fitcrew.validatorservice.validator.util.ClientUtil;
import com.fitcrew.validatorservice.validator.util.TrainerUtil;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static com.fitcrew.validatorservice.validator.util.AdminUtil.*;
import static com.fitcrew.validatorservice.validator.util.ClientUtil.*;
import static com.fitcrew.validatorservice.validator.util.EmailUtil.*;
import static com.fitcrew.validatorservice.validator.util.LoginUtil.*;
import static com.fitcrew.validatorservice.validator.util.LoginUtil.PASSWORD;
import static com.fitcrew.validatorservice.validator.util.RatingUtil.*;
import static com.fitcrew.validatorservice.validator.util.RatingUtil.TRAINER_FIRST_NAME;
import static com.fitcrew.validatorservice.validator.util.RatingUtil.TRAINER_LAST_NAME;
import static com.fitcrew.validatorservice.validator.util.TrainerUtil.*;
import static com.fitcrew.validatorservice.validator.util.TrainingUtil.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ValidatorFactoryTest {

    private final DataValidationProvider dataValidationProvider = mock(DataValidationProvider.class);
    private final EmailValidator emailValidator = new EmailValidator();
    private final TrainerValidator trainerValidator = new TrainerValidator(dataValidationProvider);
    private final TrainingValidator trainingValidator = new TrainingValidator();
    private final LoginValidator loginValidator = new LoginValidator();
    private final ClientValidator clientValidator = new ClientValidator(dataValidationProvider);
    private final AdminValidator adminValidator = new AdminValidator(dataValidationProvider);

    private final ValidatorFactory validatorFactory = new ValidatorFactory(
            emailValidator, trainerValidator, trainingValidator, loginValidator, clientValidator, adminValidator);

    @Test
    void shouldReturnTrueAfterEmailDtoValidate() {
        //given
        var email = getEmailDto(SENDER, RECIPIENT, SUBJECT, BODY_OF_MESSAGE);

        //when
        var result = validatorFactory.validate(email);

        //then
        assertTrueValidation(result);
    }

    @Test
    void shouldReturnFalseAfterEmailDtoValidate() {
        //given
        var email = getEmailDto(null, RECIPIENT, SUBJECT, BODY_OF_MESSAGE);

        //when
        var result = validatorFactory.validate(email);

        //then
        assertFalseValidation(result);
    }

    @Test
    void shouldReturnTrueAfterTrainerDtoValidate() {
        //given
        var trainer =
                getTrainerDto(TRAINER_ID, TRAINER_FIRST_NAME, TRAINER_LAST_NAME, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, TRAINER_DESCRIPTION, TrainerUtil.PASSWORD);
        when(dataValidationProvider.getTrainerById(anyString()))
                .thenReturn(Mono.empty());

        //when
        var result = validatorFactory.validate(trainer);

        //then
        assertTrueValidation(result);
    }

    @Test
    void shouldReturnFalseAfterTrainerDtoValidate() {
        //given
        var trainer =
                getTrainerDto(TRAINER_ID, null, TRAINER_LAST_NAME, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, TRAINER_DESCRIPTION, TrainerUtil.PASSWORD);
        when(dataValidationProvider.getTrainerById(anyString()))
                .thenReturn(Mono.just(trainer));

        //when
        var result = validatorFactory.validate(trainer);

        //then
        assertFalseValidation(result);
    }

    @Test
    void shouldReturnTrueAfterTrainingDtoValidate() {
        //given
        var client = Collections.singletonList(CLIENT);
        var training = getTrainingDto(TRAINER_EMAIL, client, DESCRIPTION, TRAINING, TRAINING_NAME);

        //when
        var result = validatorFactory.validate(training);

        //then
        assertTrueValidation(result);
    }

    @Test
    void shouldReturnFalseAfterTrainingDtoValidate() {
        //given
        var training = getTrainingDto(TRAINER_EMAIL, null, DESCRIPTION, TRAINING, TRAINING_NAME);

        //when
        var result = validatorFactory.validate(training);

        //then
        assertFalseValidation(result);
    }

    @Test
    void shouldReturnTrueAfterLoginDtoValidate() {
        //given
        var login = getLoginDto(LOGIN, PASSWORD);

        //when
        var result = validatorFactory.validate(login);

        //then
        assertTrueValidation(result);
    }

    @Test
    void shouldReturnFalseAfterLoginDtoValidate() {
        //given
        var login = getLoginDto(null, PASSWORD);

        //when
        var result = validatorFactory.validate(login);

        //then
        assertFalseValidation(result);
    }

    @Test
    void shouldReturnTrueAfterClientDtoValidate() {
        //given
        var client =
                getClientDto(CLIENT_ID, CLIENT_FIRST_NAME, CLIENT_LAST_NAME, CLIENT_DATE_OF_BIRTH, ClientUtil.PASSWORD, CLIENT_PHONE_NUMBER, CLIENT_EMAIL);
        when(dataValidationProvider.getClientById(anyString()))
                .thenReturn(Mono.empty());

        //when
        var result = validatorFactory.validate(client);

        //then
        assertTrueValidation(result);
    }

    @Test
    void shouldReturnFalseAfterClientDtoValidate() {
        //given
        var client =
                getClientDto(CLIENT_ID, null, CLIENT_LAST_NAME, CLIENT_DATE_OF_BIRTH, ClientUtil.PASSWORD, CLIENT_PHONE_NUMBER, CLIENT_EMAIL);
        when(dataValidationProvider.getClientById(anyString()))
                .thenReturn(Mono.just(client));

        //when
        var result = validatorFactory.validate(client);

        //then
        assertFalseValidation(result);
    }

    @Test
    void shouldReturnTrueAfterAdminDtoValidate() {
        //given
        var admin = getAdminDto(ADMIN_ID, ADMIN_EMAIL, ADMIN_FIRST_NAME, ADMIN_LAST_NAME, AdminUtil.PASSWORD);
        when(dataValidationProvider.getAdminById(anyString()))
                .thenReturn(Mono.empty());

        //when
        var result = validatorFactory.validate(admin);

        //then
        assertTrueValidation(result);
    }

    @Test
    void shouldReturnFalseAfterAdminDtoValidate() {
        //given
        var admin = getAdminDto(ADMIN_ID, ADMIN_EMAIL, ADMIN_FIRST_NAME, ADMIN_LAST_NAME, AdminUtil.PASSWORD);
        when(dataValidationProvider.getAdminById(anyString()))
                .thenReturn(Mono.just(admin));

        //when
        var result = validatorFactory.validate(admin);

        //then
        assertFalseValidation(result);
    }

    private <T, G> void assertTrueValidation(Mono<Validation<Seq<G>, T>> result) {
        StepVerifier.create(result)
                .expectSubscription()
                .expectNextMatches(Validation::isValid)
                .verifyComplete();
    }

    private <T, G> void assertFalseValidation(Mono<Validation<Seq<G>, T>> result) {
        StepVerifier.create(result)
                .expectSubscription()
                .expectNextMatches(Validation::isInvalid)
                .verifyComplete();
    }
}