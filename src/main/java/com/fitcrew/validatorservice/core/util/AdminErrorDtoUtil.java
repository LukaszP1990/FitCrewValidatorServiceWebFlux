package com.fitcrew.validatorservice.core.util;

import com.fitcrew.FitCrewAppConstant.message.ValidationErrorMessage;

import com.fitcrew.FitCrewAppModel.domain.dto.AdminDto;
import com.fitcrew.validatorservice.core.error.admin.*;
import com.fitcrew.validatorservice.core.error.model.ErrorType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminErrorDtoUtil {
    public static ValueAdminErrorDto buildValueAdminErrorDto(AdminDto adminDto) {
        return ValueAdminErrorDto.builder()
                .type(ErrorType.VALUE_ERROR)
                .value(ValidationErrorMessage.NOT_ALL_ADMIN_VALUES)
                .adminDto(adminDto)
                .build();
    }

    public static FirstNameErrorDto buildFirstNameErrorDto(AdminDto adminDto) {
        return FirstNameErrorDto.builder()
                .type(ErrorType.FIRST_NAME_ERROR)
                .value(ValidationErrorMessage.FIRST_NAME_ERROR_MESSAGE)
                .adminDto(adminDto)
                .build();
    }

    public static LastNameErrorDto buildLastNameErrorDto(AdminDto adminDto) {
        return LastNameErrorDto.builder()
                .type(ErrorType.LAST_NAME_ERROR)
                .value(ValidationErrorMessage.LAST_NAME_ERROR_MESSAGE)
                .adminDto(adminDto)
                .build();
    }

    public static AdminIdErrorDto buildAdminIdErrorDto(AdminDto adminDto) {
        return AdminIdErrorDto.builder()
                .type(ErrorType.ADMIN_ID_ERROR)
                .value(ValidationErrorMessage.ADMIN_ID_ERROR_MESSAGE)
                .adminDto(adminDto)
                .build();
    }

    public static EmailRegexErrorDto buildEmailRegexErrorDto(AdminDto adminDto) {
        return EmailRegexErrorDto.builder()
                .type(ErrorType.EMAIL_REGEX_ERROR)
                .value(ValidationErrorMessage.EMAIL_FORMAT)
                .adminDto(adminDto)
                .build();
    }
}
