package com.fitcrew.validatorservice.validator.util;

import com.fitcrew.FitCrewAppModel.domain.dto.RatingTrainerDto;

public class RatingUtil {

    public static final String TRAINER_FIRST_NAME = "firstName";
    public static final String TRAINER_LAST_NAME = "lastName";

    public static RatingTrainerDto getRatingTrainerDto(String firstName,
                                                       String lastName,
                                                       Integer rating) {
        return RatingTrainerDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .rating(rating)
                .build();
    }

    public static class RatingData{
        private final RatingTrainerDto ratingTrainerDto;
        private final boolean result;

        public RatingData(RatingTrainerDto ratingTrainerDto, boolean result) {
            this.ratingTrainerDto = ratingTrainerDto;
            this.result = result;
        }

        public RatingTrainerDto getRatingTrainerDto() {
            return ratingTrainerDto;
        }

        public boolean isResult() {
            return result;
        }
    }
}
