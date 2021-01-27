package com.fitcrew.validatorservice.core.util;

import com.fitcrew.validatorservice.core.error.model.ValidationAuthReqErrorDto;
import org.junit.jupiter.api.Test;

import static com.fitcrew.FitCrewAppConstant.message.type.RoleType.ROLE_TRAINER;
import static com.fitcrew.validatorservice.core.error.model.ErrorType.EMAIL_REGEX_ERROR;
import static com.fitcrew.validatorservice.core.error.model.ErrorType.VALUE_ERROR;
import static com.fitcrew.validatorservice.validator.util.AuthRequestUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class AuthRequestErrorDtoUtilTest {

    @Test
    void shouldBuildValueAuthRequestErrorDto() {
        //given
        var authRequest = getAuthRequest(LOGIN, null, ROLE_TRAINER);

        //when
        var valueLoginErrorDto = AuthRequestErrorDtoUtil.buildValueAuthRequestErrorDto(authRequest);

        //then
        checkAssertions(
                valueLoginErrorDto,
                VALUE_ERROR,
                valueLoginErrorDto.getAuthRequest().getPassword()
        );
    }

    @Test
    void shouldBuildEmailRegexErrorDto() {
        //given
        var authRequest = getAuthRequest(null, PASSWORD, ROLE_TRAINER);

        //when
        var emailRegexErrorDto = AuthRequestErrorDtoUtil.buildEmailRegexErrorDto(authRequest);

        //then
        checkAssertions(
                emailRegexErrorDto,
                EMAIL_REGEX_ERROR,
                emailRegexErrorDto.getAuthRequest().getEmail()
        );
    }

    private <T extends ValidationAuthReqErrorDto> void checkAssertions(T errorDto,
                                                                       String errorType,
                                                                       String errorValue) {
        assertAll(() -> {
            assertNotNull(errorDto);
            assertEquals(errorType, errorDto.getType());
            assertNull(errorValue);
        });
    }
}