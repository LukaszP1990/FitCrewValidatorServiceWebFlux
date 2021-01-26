package com.fitcrew.validatorservice.core.util;

import com.fitcrew.FitCrewAppConstant.message.ValidationErrorMessage;
import com.fitcrew.FitCrewAppModel.domain.dto.LoginDto;
import com.fitcrew.validatorservice.core.error.login.EmailRegexErrorDto;
import com.fitcrew.validatorservice.core.error.login.ValueLoginErrorDto;
import com.fitcrew.validatorservice.core.error.model.ErrorType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginErrorDtoUtil {

    public static ValueLoginErrorDto buildValueLoginErrorDto(LoginDto loginDto) {
        return ValueLoginErrorDto.builder()
                .type(ErrorType.VALUE_ERROR)
                .value(ValidationErrorMessage.NOT_ALL_LOGIN_VALUES)
                .loginDto(loginDto)
                .build();
    }

    public static EmailRegexErrorDto buildEmailRegexErrorDto(LoginDto loginDto) {
        return EmailRegexErrorDto.builder()
                .type(ErrorType.EMAIL_REGEX_ERROR)
                .value(ValidationErrorMessage.EMAIL_FORMAT)
                .loginDto(loginDto)
                .build();
    }
}
