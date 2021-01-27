package com.fitcrew.validatorservice.core.util;

import com.fitcrew.FitCrewAppConstant.message.ValidationErrorMessage;
import com.fitcrew.jwt.model.AuthenticationRequest;
import com.fitcrew.validatorservice.core.error.authentication.EmailRegexErrorDto;
import com.fitcrew.validatorservice.core.error.authentication.ValueAuthReqErrorDto;
import com.fitcrew.validatorservice.core.error.model.ErrorType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthRequestErrorDtoUtil {

    public static ValueAuthReqErrorDto buildValueAuthRequestErrorDto(AuthenticationRequest authRequest) {
        return ValueAuthReqErrorDto.builder()
                .type(ErrorType.VALUE_ERROR)
                .value(ValidationErrorMessage.NOT_ALL_LOGIN_VALUES)
                .authRequest(authRequest)
                .build();
    }

    public static EmailRegexErrorDto buildEmailRegexErrorDto(AuthenticationRequest authRequest) {
        return EmailRegexErrorDto.builder()
                .type(ErrorType.EMAIL_REGEX_ERROR)
                .value(ValidationErrorMessage.EMAIL_FORMAT)
                .authRequest(authRequest)
                .build();
    }
}
