package com.fitcrew.validatorservice.factory;

import com.fitcrew.FitCrewAppModel.domain.dto.*;
import com.fitcrew.jwt.model.AuthenticationRequest;
import com.fitcrew.validatorservice.core.error.model.*;
import com.fitcrew.validatorservice.validator.admin.AdminValidator;
import com.fitcrew.validatorservice.validator.client.ClientValidator;
import com.fitcrew.validatorservice.validator.email.EmailValidator;
import com.fitcrew.validatorservice.validator.authentication.AuthRequestValidator;
import com.fitcrew.validatorservice.validator.trainer.TrainerValidator;
import com.fitcrew.validatorservice.validator.training.TrainingValidator;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ValidatorFactory {

    private final EmailValidator emailValidator;
    private final TrainerValidator trainerValidator;
    private final TrainingValidator trainingValidator;
    private final AuthRequestValidator authRequestValidator;
    private final ClientValidator clientValidator;
    private final AdminValidator adminValidator;

    public ValidatorFactory(final EmailValidator emailValidator,
                            final TrainerValidator trainerValidator,
                            final TrainingValidator trainingValidator,
                            final AuthRequestValidator authRequestValidator,
                            final ClientValidator clientValidator,
                            final AdminValidator adminValidator) {
        this.emailValidator = emailValidator;
        this.trainerValidator = trainerValidator;
        this.trainingValidator = trainingValidator;
        this.authRequestValidator = authRequestValidator;
        this.clientValidator = clientValidator;
        this.adminValidator = adminValidator;
    }

    public Mono<Validation<Seq<ValidationEmailErrorDto>, EmailDto>> validate(EmailDto emailDto) {
        log.info("Validation of: {}", emailDto);
        return Mono.just(emailValidator.validate(emailDto));
    }

    public Mono<Validation<Seq<ValidationTrainerErrorDto>, TrainerDto>> validate(TrainerDto trainerDto) {
        log.info("Validation of: {}", trainerDto);
        return Mono.just(trainerValidator.validate(trainerDto));
    }

    public Mono<Validation<Seq<ValidationTrainingErrorDto>, TrainingDto>> validate(TrainingDto trainingDto) {
        log.info("Validation of: {}", trainingDto);
        return Mono.just(trainingValidator.validate(trainingDto));
    }

    public Mono<Validation<Seq<ValidationAuthReqErrorDto>, AuthenticationRequest>> validate(AuthenticationRequest authenticationRequest) {
        log.info("Validation of: {}", authenticationRequest);
        return Mono.just(authRequestValidator.validate(authenticationRequest));
    }

    public Mono<Validation<Seq<ValidationClientErrorDto>, ClientDto>> validate(ClientDto clientDto) {
        log.info("Validation of: {}", clientDto);
        return Mono.just(clientValidator.validate(clientDto));
    }

    public Mono<Validation<Seq<ValidationAdminErrorDto>, AdminDto>> validate(AdminDto adminDto) {
        log.info("Validation of: {}", adminDto);
        return Mono.just(adminValidator.validate(adminDto));
    }
}
