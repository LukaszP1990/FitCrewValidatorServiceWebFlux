package com.fitcrew.validatorservice.core.error.rating;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fitcrew.FitCrewAppModel.domain.dto.RatingTrainerDto;
import com.fitcrew.validatorservice.core.error.model.ValidationRatingErrorDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@ToString
@JsonIgnoreProperties({"ratingTrainerDto"})
public class LastNameErrorDto extends ValidationRatingErrorDto {
    private final String value;

    @Builder
    @ConstructorProperties({"type", "value"})
    protected LastNameErrorDto(final String type,
                               final String value,
                               final RatingTrainerDto ratingTrainerDto) {
        super(type, ratingTrainerDto);
        this.value = value;
    }
}
