package com.fitcrew.validatorservice.validator.authentication;

import com.fitcrew.jwt.model.AuthenticationRequest;
import com.fitcrew.validatorservice.core.error.model.ValidationAuthReqErrorDto;
import com.fitcrew.validatorservice.core.util.AuthRequestErrorDtoUtil;
import com.fitcrew.validatorservice.validator.predicates.AuthRequestAllValuesPredicate;
import com.fitcrew.validatorservice.validator.predicates.EmailRegexPredicate;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.springframework.stereotype.Component;

@Component
public class AuthRequestValidator {

    public Validation<Seq<ValidationAuthReqErrorDto>, AuthenticationRequest> validate(AuthenticationRequest dto) {
        return Validation.combine(
                validateValue(dto),
                validateEmailRegex(dto))
                .ap((order1, order2) -> order2);
    }

    Validation<ValidationAuthReqErrorDto, AuthenticationRequest> validateValue(AuthenticationRequest authRequest) {
        return new AuthRequestAllValuesPredicate().test(authRequest)
                ? Validation.valid(authRequest)
                : Validation.invalid(AuthRequestErrorDtoUtil.buildValueAuthRequestErrorDto(authRequest));
    }

    Validation<ValidationAuthReqErrorDto, AuthenticationRequest> validateEmailRegex(AuthenticationRequest authRequest) {
        return new EmailRegexPredicate().test(authRequest.getEmail())
                ? Validation.valid(authRequest)
                : Validation.invalid(AuthRequestErrorDtoUtil.buildEmailRegexErrorDto(authRequest));
    }
}
