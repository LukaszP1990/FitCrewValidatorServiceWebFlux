package com.fitcrew.validatorservice.core.util;

import com.fitcrew.validatorservice.core.error.model.ValidationAdminErrorDto;
import org.junit.jupiter.api.Test;

import static com.fitcrew.validatorservice.core.error.model.ErrorType.*;
import static com.fitcrew.validatorservice.validator.util.AdminUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class AdminErrorDtoUtilTest {

    @Test
    void shouldBuildValueAdminErrorDto() {
        //given
        var admin = getAdminDto(null, ADMIN_EMAIL, ADMIN_FIRST_NAME, ADMIN_LAST_NAME, PASSWORD);

        //when
        var valueAdminErrorDto = AdminErrorDtoUtil.buildValueAdminErrorDto(admin);

        //then
        checkAssertions(
                valueAdminErrorDto,
                VALUE_ERROR,
                valueAdminErrorDto.getAdminDto().getAdminId()
        );
    }

    @Test
    void shouldBuildFirstNameErrorDto() {
        //given
        var admin = getAdminDto(ADMIN_ID, ADMIN_EMAIL, null, ADMIN_LAST_NAME, PASSWORD);

        //when
        var firstNameErrorDto = AdminErrorDtoUtil.buildFirstNameErrorDto(admin);

        //then
        checkAssertions(
                firstNameErrorDto,
                FIRST_NAME_ERROR,
                firstNameErrorDto.getAdminDto().getFirstName()
        );
    }

    @Test
    void shouldBuildLastNameErrorDto() {
        //given
        var admin = getAdminDto(ADMIN_ID, ADMIN_EMAIL, ADMIN_FIRST_NAME, null, PASSWORD);

        //when
        var lastNameErrorDto = AdminErrorDtoUtil.buildLastNameErrorDto(admin);

        //then
        checkAssertions(
                lastNameErrorDto,
                LAST_NAME_ERROR,
                lastNameErrorDto.getAdminDto().getLastName()
        );
    }


    @Test
    void shouldBuildAdminIdErrorDto() {
        //given
        var admin = getAdminDto(null, ADMIN_EMAIL, ADMIN_FIRST_NAME, ADMIN_LAST_NAME, PASSWORD);

        //when
        var adminIdErrorDto = AdminErrorDtoUtil.buildAdminIdErrorDto(admin);

        //then
        checkAssertions(
                adminIdErrorDto,
                ADMIN_ID_ERROR,
                adminIdErrorDto.getAdminDto().getAdminId()
        );
    }

    @Test
    void shouldBuildEmailRegexErrorDto() {
        //given
        var admin = getAdminDto(ADMIN_ID, null, ADMIN_FIRST_NAME, ADMIN_LAST_NAME, PASSWORD);

        //when
        var emailRegexErrorDto = AdminErrorDtoUtil.buildEmailRegexErrorDto(admin);

        //then
        checkAssertions(
                emailRegexErrorDto,
                EMAIL_REGEX_ERROR,
                emailRegexErrorDto.getAdminDto().getEmail()
        );
    }

    private <T extends ValidationAdminErrorDto> void checkAssertions(T errorDto,
                                                                     String errorType,
                                                                     String errorValue) {
        assertAll(() -> {
            assertNotNull(errorDto);
            assertEquals(errorType, errorDto.getType());
            assertNull(errorValue);
        });
    }
}