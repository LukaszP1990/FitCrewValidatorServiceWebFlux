package com.fitcrew.validatorservice.core.error.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fitcrew.FitCrewAppModel.domain.dto.TrainerDto;
import com.fitcrew.validatorservice.core.error.trainer.*;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ValueTrainerErrorDto.class, name = "TRAINER_VALUES_REQUIREMENTS"),
        @JsonSubTypes.Type(value = FirstNameErrorDto.class, name = "FIRST_NAME_LENGTH"),
        @JsonSubTypes.Type(value = LastNameErrorDto.class, name = "LAST_NAME_LENGTH"),
        @JsonSubTypes.Type(value = DateOfBirthErrorDto.class, name = "DAY_OF_BIRTH_LENGTH"),
        @JsonSubTypes.Type(value = DescriptionErrorDto.class, name = "DESCRIPTION_LENGTH"),
        @JsonSubTypes.Type(value = TrainerIdErrorDto.class, name = "TRAINER_UNIQUE_ID"),
})
public abstract class ValidationTrainerErrorDto {

    @Getter
    private final String type;

    @JsonIgnore
    @Getter
    private final TrainerDto trainerDto;

    protected ValidationTrainerErrorDto(String type,
                                        TrainerDto trainerDto) {
        this.type = Objects.requireNonNull(type);
        this.trainerDto = trainerDto;
    }
}
