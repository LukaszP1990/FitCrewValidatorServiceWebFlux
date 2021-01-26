package com.fitcrew.validatorservice.core.util;

import com.fitcrew.validatorservice.core.error.model.ValidationClientErrorDto;
import org.junit.jupiter.api.Test;

import static com.fitcrew.validatorservice.core.error.model.ErrorType.*;
import static com.fitcrew.validatorservice.validator.util.AdminUtil.PASSWORD;
import static com.fitcrew.validatorservice.validator.util.ClientUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class ClientErrorDtoUtilTest {

    @Test
    void shouldBuildValueClientErrorDto() {
        //given
        var client =
                getClientDto(null, CLIENT_FIRST_NAME, CLIENT_LAST_NAME, CLIENT_DATE_OF_BIRTH, PASSWORD, CLIENT_PHONE_NUMBER, CLIENT_EMAIL);

        //when
        var valueClientErrorDto = ClientErrorDtoUtil.buildValueClientErrorDto(client);

        //then
        checkAssertions(
                valueClientErrorDto,
                VALUE_ERROR,
                valueClientErrorDto.getClientDto().getClientId()
        );
    }

    @Test
    void shouldBuildFirstNameErrorDto() {
        //given
        var client =
                getClientDto(CLIENT_ID, null, CLIENT_LAST_NAME, CLIENT_DATE_OF_BIRTH, PASSWORD, CLIENT_PHONE_NUMBER, CLIENT_EMAIL);

        //when
        var firstNameErrorDto = ClientErrorDtoUtil.buildFirstNameErrorDto(client);

        //then
        checkAssertions(
                firstNameErrorDto,
                FIRST_NAME_ERROR,
                firstNameErrorDto.getClientDto().getFirstName()
        );
    }

    @Test
    void shouldBuildLastNameErrorDto() {
        //given
        var client =
                getClientDto(CLIENT_ID, CLIENT_FIRST_NAME, null, CLIENT_DATE_OF_BIRTH, PASSWORD, CLIENT_PHONE_NUMBER, CLIENT_EMAIL);

        //when
        var lastNameErrorDto = ClientErrorDtoUtil.buildLastNameErrorDto(client);

        //then
        checkAssertions(
                lastNameErrorDto,
                LAST_NAME_ERROR,
                lastNameErrorDto.getClientDto().getLastName()
        );
    }

    @Test
    void shouldBuildDateOfBirthErrorDto() {
        //given
        var client =
                getClientDto(CLIENT_ID, CLIENT_FIRST_NAME, CLIENT_LAST_NAME, null, PASSWORD, CLIENT_PHONE_NUMBER, CLIENT_EMAIL);

        //when
        var lastNameErrorDto = ClientErrorDtoUtil.buildDateOfBirthErrorDto(client);

        //then
        checkAssertions(
                lastNameErrorDto,
                DATE_OF_BIRTH_ERROR,
                lastNameErrorDto.getClientDto().getDateOfBirth()
        );
    }

    @Test
    void shouldBuildPhoneRegexErrorDto() {
        //given
        var client =
                getClientDto(CLIENT_ID, CLIENT_FIRST_NAME, CLIENT_LAST_NAME, CLIENT_DATE_OF_BIRTH, PASSWORD, null, CLIENT_EMAIL);

        //when
        var phoneRegexErrorDto = ClientErrorDtoUtil.buildPhoneRegexErrorDto(client);

        //then
        checkAssertions(
                phoneRegexErrorDto,
                PHONE_REGEX_ERROR,
                phoneRegexErrorDto.getClientDto().getPhone()
        );

    }

    @Test
    void shouldBuildClientIdErrorDto() {
        //given
        var client =
                getClientDto(null, CLIENT_FIRST_NAME, CLIENT_LAST_NAME, CLIENT_DATE_OF_BIRTH, PASSWORD, CLIENT_PHONE_NUMBER, CLIENT_EMAIL);

        //when
        var clientIdErrorDto = ClientErrorDtoUtil.buildClientIdErrorDto(client);

        //then
        checkAssertions(
                clientIdErrorDto,
                CLIENT_ID_ERROR,
                clientIdErrorDto.getClientDto().getClientId()
        );
    }

    private <T extends ValidationClientErrorDto> void checkAssertions(T errorDto,
                                                                      String errorType,
                                                                      String errorValue) {
        assertAll(() -> {
            assertNotNull(errorDto);
            assertEquals(errorType, errorDto.getType());
            assertNull(errorValue);
        });
    }
}