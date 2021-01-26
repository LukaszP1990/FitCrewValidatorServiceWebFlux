package com.fitcrew.validatorservice.core.error.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fitcrew.FitCrewAppModel.domain.dto.RatingTrainerDto;
import com.fitcrew.validatorservice.core.error.email.ValueEmailErrorDto;
import com.fitcrew.validatorservice.core.error.rating.FirstNameErrorDto;
import com.fitcrew.validatorservice.core.error.rating.LastNameErrorDto;
import com.fitcrew.validatorservice.core.error.rating.RatingErrorDto;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ValueEmailErrorDto.class, name = "RATING_VALUES_REQUIREMENTS"),
        @JsonSubTypes.Type(value = FirstNameErrorDto.class, name = "FIRST_NAME_LENGTH"),
        @JsonSubTypes.Type(value = LastNameErrorDto.class, name = "LAST_NAME_LENGTH"),
        @JsonSubTypes.Type(value = RatingErrorDto.class, name = "RATING_RANGE"),
})
public abstract class ValidationRatingErrorDto {

    @Getter
    private final String type;

    @JsonIgnore
    @Getter
    private final RatingTrainerDto ratingTrainerDto;

    protected ValidationRatingErrorDto(String type,
                                       RatingTrainerDto ratingTrainerDto) {
        this.type = Objects.requireNonNull(type);
        this.ratingTrainerDto = ratingTrainerDto;
    }
}
