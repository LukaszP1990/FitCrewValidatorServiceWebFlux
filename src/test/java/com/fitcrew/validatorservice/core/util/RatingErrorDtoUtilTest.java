package com.fitcrew.validatorservice.core.util;

import com.fitcrew.validatorservice.core.error.model.ValidationRatingErrorDto;
import org.junit.jupiter.api.Test;

import static com.fitcrew.validatorservice.core.error.model.ErrorType.*;
import static com.fitcrew.validatorservice.validator.util.RatingUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class RatingErrorDtoUtilTest {

    @Test
    void shouldBuildValueRatingErrorDto() {
        //given
        var rating = getRatingTrainerDto(null, TRAINER_LAST_NAME, 1);

        //when
        var valueRatingErrorDto = RatingErrorDtoUtil.buildValueRatingErrorDto(rating);

        //then
        checkAssertions(
                valueRatingErrorDto,
                VALUE_ERROR,
                valueRatingErrorDto.getRatingTrainerDto().getFirstName()
        );
    }

    @Test
    void shouldBuildRatingErrorDto() {
        //given
        var rating = getRatingTrainerDto(TRAINER_FIRST_NAME, TRAINER_LAST_NAME, 11);

        //when
        var ratingErrorDto = RatingErrorDtoUtil.buildRatingErrorDto(rating);

        //then
        assertAll(() -> {
            assertNotNull(ratingErrorDto);
            assertEquals(RATING_ERROR, ratingErrorDto.getType());
            assertEquals(11, ratingErrorDto.getRatingTrainerDto().getRating());
        });
    }

    @Test
    void shouldBuildFirstNameErrorDto() {
        //given
        var rating = getRatingTrainerDto(null, TRAINER_LAST_NAME, 1);

        //when
        var firstNameErrorDto = RatingErrorDtoUtil.buildFirstNameErrorDto(rating);

        //then
        checkAssertions(
                firstNameErrorDto,
                FIRST_NAME_ERROR,
                firstNameErrorDto.getRatingTrainerDto().getFirstName()
        );
    }

    @Test
    void shouldBuildLastNameErrorDto() {
        //given
        var rating = getRatingTrainerDto(TRAINER_FIRST_NAME, null, 1);

        //when
        var lastNameErrorDto = RatingErrorDtoUtil.buildLastNameErrorDto(rating);

        //then
        checkAssertions(
                lastNameErrorDto,
                LAST_NAME_ERROR,
                lastNameErrorDto.getRatingTrainerDto().getLastName()
        );
    }

    private <T extends ValidationRatingErrorDto> void checkAssertions(T errorDto,
                                                                      String errorType,
                                                                      String errorValue) {
        assertAll(() -> {
            assertNotNull(errorDto);
            assertEquals(errorType, errorDto.getType());
            assertNull(errorValue);
        });
    }
}