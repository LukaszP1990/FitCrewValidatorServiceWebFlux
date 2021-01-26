package com.fitcrew.validatorservice.validator.predicates;

import com.fitcrew.FitCrewAppModel.domain.dto.RatingTrainerDto;
import com.fitcrew.validatorservice.core.util.ValueUtil;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RatingTrainerDtoAllValuesPredicate implements Predicate<RatingTrainerDto> {

    @Override
    public boolean test(RatingTrainerDto ratingTrainerDto) {
        var listOfFields = Stream.of(
                ratingTrainerDto.getFirstName(),
                ratingTrainerDto.getLastName(),
                ratingTrainerDto.getRating())
                .collect(Collectors.toList());
        return ValueUtil.validateStrings(listOfFields);
    }
}
