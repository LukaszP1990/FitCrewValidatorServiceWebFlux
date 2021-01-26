package com.fitcrew.validatorservice.validator.predicates;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.fitcrew.validatorservice.validator.util.RatingUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class RatingTrainerDtoAllValuesPredicateTest {

    private final RatingTrainerDtoAllValuesPredicate predicate = new RatingTrainerDtoAllValuesPredicate();

    static Stream<RatingData> ratingDataProvider() {
        var ratingTrainerDtoWithoutFirstName = getRatingTrainerDto(null, TRAINER_LAST_NAME, 1);
        var ratingTrainerDtoWithoutLastName = getRatingTrainerDto(TRAINER_FIRST_NAME, null, 1);
        var ratingTrainerDtoWithoutRating = getRatingTrainerDto(TRAINER_FIRST_NAME, TRAINER_LAST_NAME, null);
        var ratingTrainerDtoWithAllData = getRatingTrainerDto(TRAINER_FIRST_NAME, TRAINER_LAST_NAME, 1);

        return Stream.of(
                new RatingData(ratingTrainerDtoWithoutFirstName, false),
                new RatingData(ratingTrainerDtoWithoutLastName, false),
                new RatingData(ratingTrainerDtoWithoutRating, false),
                new RatingData(ratingTrainerDtoWithAllData, true)
        );
    }

    @ParameterizedTest
    @MethodSource("ratingDataProvider")
    void ratingTrainerDtoAllValuesPredicateTest(RatingData ratingData) {
        boolean result = predicate.test(ratingData.getRatingTrainerDto());
        assertEquals(result, ratingData.isResult());
    }
}