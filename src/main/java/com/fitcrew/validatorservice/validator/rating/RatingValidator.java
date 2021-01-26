package com.fitcrew.validatorservice.validator.rating;

import com.fitcrew.FitCrewAppModel.domain.dto.RatingTrainerDto;
import com.fitcrew.validatorservice.core.error.model.ValidationRatingErrorDto;
import com.fitcrew.validatorservice.core.util.RatingErrorDtoUtil;
import com.fitcrew.validatorservice.validator.predicates.RatingPredicate;
import com.fitcrew.validatorservice.validator.predicates.RatingTrainerDtoAllValuesPredicate;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.springframework.stereotype.Component;

@Component
public class RatingValidator {

    public Validation<Seq<ValidationRatingErrorDto>, RatingTrainerDto> validate(RatingTrainerDto dto) {
        return Validation.combine(
                validateValue(dto),
                validateRating(dto),
                validateFirstName(dto),
                validateLastName(dto))
                .ap((order1, order2, order3, order4) -> order4);
    }

    Validation<ValidationRatingErrorDto, RatingTrainerDto> validateValue(RatingTrainerDto ratingTrainerDto) {
        return new RatingTrainerDtoAllValuesPredicate().test(ratingTrainerDto)
                ? Validation.valid(ratingTrainerDto)
                : Validation.invalid(RatingErrorDtoUtil.buildValueRatingErrorDto(ratingTrainerDto));
    }

    Validation<ValidationRatingErrorDto, RatingTrainerDto> validateRating(RatingTrainerDto ratingTrainerDto) {
        return new RatingPredicate().test(ratingTrainerDto)
                ? Validation.valid(ratingTrainerDto)
                : Validation.invalid(RatingErrorDtoUtil.buildRatingErrorDto(ratingTrainerDto));
    }

    Validation<ValidationRatingErrorDto, RatingTrainerDto> validateFirstName(RatingTrainerDto ratingTrainerDto) {
        return new RatingPredicate().test(ratingTrainerDto)
                ? Validation.valid(ratingTrainerDto)
                : Validation.invalid(RatingErrorDtoUtil.buildFirstNameErrorDto(ratingTrainerDto));
    }

    Validation<ValidationRatingErrorDto, RatingTrainerDto> validateLastName(RatingTrainerDto ratingTrainerDto) {
        return new RatingPredicate().test(ratingTrainerDto)
                ? Validation.valid(ratingTrainerDto)
                : Validation.invalid(RatingErrorDtoUtil.buildLastNameErrorDto(ratingTrainerDto));
    }
}
