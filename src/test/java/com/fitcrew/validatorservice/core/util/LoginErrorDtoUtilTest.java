package com.fitcrew.validatorservice.core.util;

import com.fitcrew.validatorservice.core.error.model.ValidationLoginErrorDto;
import org.junit.jupiter.api.Test;

import static com.fitcrew.validatorservice.core.error.model.ErrorType.EMAIL_REGEX_ERROR;
import static com.fitcrew.validatorservice.core.error.model.ErrorType.VALUE_ERROR;
import static com.fitcrew.validatorservice.validator.util.LoginUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginErrorDtoUtilTest {

    @Test
    void shouldBuildValueLoginErrorDto() {
        //given
        var login = getLoginDto(LOGIN, null);

        //when
        var valueLoginErrorDto = LoginErrorDtoUtil.buildValueLoginErrorDto(login);

        //then
        checkAssertions(
                valueLoginErrorDto,
                VALUE_ERROR,
                valueLoginErrorDto.getLoginDto().getPassword()
        );
    }

    @Test
    void shouldBuildEmailRegexErrorDto() {
        //given
        var login = getLoginDto(null, PASSWORD);

        //when
        var emailRegexErrorDto = LoginErrorDtoUtil.buildEmailRegexErrorDto(login);

        //then
        checkAssertions(
                emailRegexErrorDto,
                EMAIL_REGEX_ERROR,
                emailRegexErrorDto.getLoginDto().getEmail()
        );
    }

    private <T extends ValidationLoginErrorDto> void checkAssertions(T errorDto,
                                                                     String errorType,
                                                                     String errorValue) {
        assertAll(() -> {
            assertNotNull(errorDto);
            assertEquals(errorType, errorDto.getType());
            assertNull(errorValue);
        });
    }
}