package com.fitcrew.validatorservice.core.util;

import com.fitcrew.FitCrewAppConstant.message.ValidationErrorMessage;
import com.fitcrew.FitCrewAppModel.domain.dto.ClientDto;
import com.fitcrew.validatorservice.core.error.client.*;
import com.fitcrew.validatorservice.core.error.model.ErrorType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientErrorDtoUtil {

    public static ValueClientErrorDto buildValueClientErrorDto(ClientDto clientDto) {
        return ValueClientErrorDto.builder()
                .type(ErrorType.VALUE_ERROR)
                .value(ValidationErrorMessage.NOT_ALL_CLIENT_VALUES)
                .clientDto(clientDto)
                .build();
    }

    public static FirstNameErrorDto buildFirstNameErrorDto(ClientDto clientDto) {
        return FirstNameErrorDto.builder()
                .type(ErrorType.FIRST_NAME_ERROR)
                .value(ValidationErrorMessage.FIRST_NAME_ERROR_MESSAGE)
                .clientDto(clientDto)
                .build();
    }

    public static LastNameErrorDto buildLastNameErrorDto(ClientDto clientDto) {
        return LastNameErrorDto.builder()
                .type(ErrorType.LAST_NAME_ERROR)
                .value(ValidationErrorMessage.LAST_NAME_ERROR_MESSAGE)
                .clientDto(clientDto)
                .build();
    }

    public static DateOfBirthErrorDto buildDateOfBirthErrorDto(ClientDto clientDto) {
        return DateOfBirthErrorDto.builder()
                .type(ErrorType.DATE_OF_BIRTH_ERROR)
                .value(ValidationErrorMessage.DATE_OF_BIRTH_ERROR_MESSAGE)
                .clientDto(clientDto)
                .build();
    }

    public static PhoneRegexErrorDto buildPhoneRegexErrorDto(ClientDto clientDto) {
        return PhoneRegexErrorDto.builder()
                .type(ErrorType.PHONE_REGEX_ERROR)
                .value(ValidationErrorMessage.PHONE_FORMAT)
                .clientDto(clientDto)
                .build();
    }

    public static ClientIdErrorDto buildClientIdErrorDto(ClientDto clientDto) {
        return ClientIdErrorDto.builder()
                .type(ErrorType.CLIENT_ID_ERROR)
                .value(ValidationErrorMessage.CLIENT_ID_ERROR_MESSAGE)
                .clientDto(clientDto)
                .build();
    }
}
