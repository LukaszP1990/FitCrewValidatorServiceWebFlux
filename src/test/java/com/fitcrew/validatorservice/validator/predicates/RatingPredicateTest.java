package com.fitcrew.validatorservice.validator.predicates;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.RatingUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RatingPredicateTest {

    private final RatingPredicate predicate = new RatingPredicate();

    static Stream<RatingData> ratingDataProvider() {
        var ratingTrainerDtoWithRating10 = getRatingTrainerDto(TRAINER_FIRST_NAME, TRAINER_LAST_NAME, 10);
        var ratingTrainerDtoWithRating1 = getRatingTrainerDto(TRAINER_FIRST_NAME, TRAINER_LAST_NAME, 1);
        var ratingTrainerDtoWithRating11 = getRatingTrainerDto(TRAINER_FIRST_NAME, TRAINER_LAST_NAME, 11);
        var ratingTrainerDtoWithRating0 = getRatingTrainerDto(TRAINER_FIRST_NAME, TRAINER_LAST_NAME, 0);

        return Stream.of(
                new RatingData(ratingTrainerDtoWithRating10, true),
                new RatingData(ratingTrainerDtoWithRating1, true),
                new RatingData(ratingTrainerDtoWithRating11, false),
                new RatingData(ratingTrainerDtoWithRating0, false)
        );
    }

    @ParameterizedTest
    @MethodSource("ratingDataProvider")
    void ratingPredicateTest(RatingData ratingData) {
        boolean result = predicate.test(ratingData.getRatingTrainerDto());
        assertEquals(result, ratingData.isResult());
    }
}