package com.fitcrew.validatorservice.validator.login;

import com.fitcrew.FitCrewAppModel.domain.dto.LoginDto;
import com.fitcrew.validatorservice.core.error.model.ValidationLoginErrorDto;
import com.fitcrew.validatorservice.core.util.LoginErrorDtoUtil;
import com.fitcrew.validatorservice.validator.predicates.EmailRegexPredicate;
import com.fitcrew.validatorservice.validator.predicates.LoginDtoAllValuesPredicate;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator {

    public Validation<Seq<ValidationLoginErrorDto>, LoginDto> validate(LoginDto dto) {
        return Validation.combine(
                validateValue(dto),
                validateEmailRegex(dto))
                .ap((order1, order2) -> order2);
    }

    Validation<ValidationLoginErrorDto, LoginDto> validateValue(LoginDto loginDto) {
        return new LoginDtoAllValuesPredicate().test(loginDto)
                ? Validation.valid(loginDto)
                : Validation.invalid(LoginErrorDtoUtil.buildValueLoginErrorDto(loginDto));
    }

    Validation<ValidationLoginErrorDto, LoginDto> validateEmailRegex(LoginDto loginDto) {
        return new EmailRegexPredicate().test(loginDto.getEmail())
                ? Validation.valid(loginDto)
                : Validation.invalid(LoginErrorDtoUtil.buildEmailRegexErrorDto(loginDto));
    }
}
