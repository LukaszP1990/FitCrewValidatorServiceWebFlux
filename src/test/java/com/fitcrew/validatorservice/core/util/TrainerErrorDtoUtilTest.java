package com.fitcrew.validatorservice.core.util;

import com.fitcrew.validatorservice.core.error.model.ValidationTrainerErrorDto;
import org.junit.jupiter.api.Test;

import static com.fitcrew.validatorservice.core.error.model.ErrorType.*;
import static com.fitcrew.validatorservice.validator.util.TrainerUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class TrainerErrorDtoUtilTest {

    @Test
    void shouldBuildValueTrainerErrorDto() {
        //given
        var trainer =
                getTrainerDto(null, TRAINER_FIRST_NAME, TRAINER_LAST_NAME, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, TRAINER_DESCRIPTION, PASSWORD);

        //when
        var valueTrainerErrorDto = TrainerErrorDtoUtil.buildValueTrainerErrorDto(trainer);

        //then
        checkAssertions(
                valueTrainerErrorDto,
                VALUE_ERROR,
                valueTrainerErrorDto.getTrainerDto().getTrainerId()
        );
    }

    @Test
    void shouldBuildFirstNameErrorDto() {
        //given
        var trainer =
                getTrainerDto(TRAINER_ID, null, TRAINER_LAST_NAME, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, TRAINER_DESCRIPTION, PASSWORD);

        //when
        var firstNameErrorDto = TrainerErrorDtoUtil.buildFirstNameErrorDto(trainer);

        //then
        checkAssertions(
                firstNameErrorDto,
                FIRST_NAME_ERROR,
                firstNameErrorDto.getTrainerDto().getFirstName()
        );
    }

    @Test
    void shouldBuildLastNameErrorDto() {
        //given
        var trainer =
                getTrainerDto(TRAINER_ID, TRAINER_FIRST_NAME, null, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, TRAINER_DESCRIPTION, PASSWORD);

        //when
        var lastNameErrorDto = TrainerErrorDtoUtil.buildLastNameErrorDto(trainer);

        //then
        checkAssertions(
                lastNameErrorDto,
                LAST_NAME_ERROR,
                lastNameErrorDto.getTrainerDto().getLastName()
        );
    }

    @Test
    void shouldBuildDateOfBirthErrorDto() {
        //given
        var trainer =
                getTrainerDto(TRAINER_ID, TRAINER_FIRST_NAME, TRAINER_LAST_NAME, TRAINER_EMAIL, null, TRAINER_PHONE_NUMBER, TRAINER_DESCRIPTION, PASSWORD);

        //when
        var dateOfBirthErrorDto = TrainerErrorDtoUtil.buildDateOfBirthErrorDto(trainer);

        //then
        checkAssertions(
                dateOfBirthErrorDto,
                DATE_OF_BIRTH_ERROR,
                dateOfBirthErrorDto.getTrainerDto().getDateOfBirth()
        );
    }

    @Test
    void shouldBuildDescriptionErrorDto() {
        //given
        var trainer =
                getTrainerDto(TRAINER_ID, TRAINER_FIRST_NAME, TRAINER_LAST_NAME, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, null, PASSWORD);

        //when
        var descriptionErrorDto = TrainerErrorDtoUtil.buildDescriptionErrorDto(trainer);

        //then
        checkAssertions(
                descriptionErrorDto,
                DESCRIPTION_ERROR,
                descriptionErrorDto.getTrainerDto().getSomethingAboutYourself()
        );
    }

    @Test
    void shouldBuildTrainerIdErrorDto() {
        //given
        var trainer =
                getTrainerDto(null, TRAINER_FIRST_NAME, TRAINER_LAST_NAME, TRAINER_EMAIL, TRAINER_DATE_OF_BIRTH, TRAINER_PHONE_NUMBER, null, PASSWORD);

        //when
        var trainerIdErrorDto = TrainerErrorDtoUtil.buildTrainerIdErrorDto(trainer);

        //then
        checkAssertions(
                trainerIdErrorDto,
                TRAINER_ID_ERROR,
                trainerIdErrorDto.getTrainerDto().getTrainerId()
        );
    }

    private <T extends ValidationTrainerErrorDto> void checkAssertions(T errorDto,
                                                                       String errorType,
                                                                       String errorValue) {
        assertAll(() -> {
            assertNotNull(errorDto);
            assertEquals(errorType, errorDto.getType());
            assertNull(errorValue);
        });
    }
}