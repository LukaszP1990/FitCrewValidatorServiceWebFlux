package com.fitcrew.validatorservice.core.util;

import com.fitcrew.FitCrewAppConstant.message.ValidationErrorMessage;
import com.fitcrew.FitCrewAppModel.domain.dto.RatingTrainerDto;
import com.fitcrew.validatorservice.core.error.model.ErrorType;
import com.fitcrew.validatorservice.core.error.rating.RatingErrorDto;
import com.fitcrew.validatorservice.core.error.rating.FirstNameErrorDto;
import com.fitcrew.validatorservice.core.error.rating.LastNameErrorDto;
import com.fitcrew.validatorservice.core.error.rating.ValueRatingErrorDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RatingErrorDtoUtil {

    public static ValueRatingErrorDto buildValueRatingErrorDto(RatingTrainerDto ratingTrainerDto) {
        return ValueRatingErrorDto.builder()
                .type(ErrorType.VALUE_ERROR)
                .value(ValidationErrorMessage.NOT_ALL_RATING_VALUES)
                .ratingTrainerDto(ratingTrainerDto)
                .build();
    }

    public static RatingErrorDto buildRatingErrorDto(RatingTrainerDto ratingTrainerDto) {
        return RatingErrorDto.builder()
                .type(ErrorType.RATING_ERROR)
                .value(ValidationErrorMessage.RATING_ERROR_MESSAGE)
                .ratingTrainerDto(ratingTrainerDto)
                .build();
    }

    public static FirstNameErrorDto buildFirstNameErrorDto(RatingTrainerDto ratingTrainerDto) {
        return FirstNameErrorDto.builder()
                .type(ErrorType.FIRST_NAME_ERROR)
                .value(ValidationErrorMessage.FIRST_NAME_ERROR_MESSAGE)
                .ratingTrainerDto(ratingTrainerDto)
                .build();
    }

    public static LastNameErrorDto buildLastNameErrorDto(RatingTrainerDto ratingTrainerDto) {
        return LastNameErrorDto.builder()
                .type(ErrorType.LAST_NAME_ERROR)
                .value(ValidationErrorMessage.FIRST_NAME_ERROR_MESSAGE)
                .ratingTrainerDto(ratingTrainerDto)
                .build();
    }
}
