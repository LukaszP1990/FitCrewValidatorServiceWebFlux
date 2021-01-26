package com.fitcrew.validatorservice.validator.predicates;

import com.fitcrew.FitCrewAppModel.domain.dto.RatingTrainerDto;

import java.util.Objects;
import java.util.function.Predicate;

public class RatingPredicate implements Predicate<RatingTrainerDto> {

    @Override
    public boolean test(RatingTrainerDto ratingTrainerDto) {
        var rating = ratingTrainerDto.getRating();
        return Objects.nonNull(rating) && rating >= 1 && rating <= 10;
    }
}
