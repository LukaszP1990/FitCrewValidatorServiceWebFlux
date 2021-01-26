package com.fitcrew.validatorservice.core.error.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fitcrew.FitCrewAppModel.domain.dto.TrainingDto;
import com.fitcrew.validatorservice.core.error.email.ValueEmailErrorDto;
import com.fitcrew.validatorservice.core.error.trainer.DescriptionErrorDto;
import com.fitcrew.validatorservice.core.error.training.EmailRegexErrorDto;
import com.fitcrew.validatorservice.core.error.training.TrainingErrorDto;
import com.fitcrew.validatorservice.core.error.training.TrainingNameErrorDto;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ValueEmailErrorDto.class, name = "TRAINER_VALUES_REQUIREMENTS"),
        @JsonSubTypes.Type(value = TrainingNameErrorDto.class, name = "TRAINING_NAME_LENGTH"),
        @JsonSubTypes.Type(value = DescriptionErrorDto.class, name = "DESCRIPTION_LENGTH"),
        @JsonSubTypes.Type(value = TrainingErrorDto.class, name = "TRAINING_LENGTH"),
        @JsonSubTypes.Type(value = EmailRegexErrorDto.class, name = "EMAIL_REGEX"),
})
public abstract class ValidationTrainingErrorDto {

    @Getter
    private final String type;

    @JsonIgnore
    @Getter
    private final TrainingDto trainingDto;

    protected ValidationTrainingErrorDto(String type,
                                         TrainingDto trainingDto) {
        this.type = Objects.requireNonNull(type);
        this.trainingDto = trainingDto;
    }
}
